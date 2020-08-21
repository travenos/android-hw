package com.example.androidhw.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidhw.MainActivityViewModel
import com.example.androidhw.R
import com.example.androidhw.di.DaggerMainActivityComponent
import com.example.common.HwApplication
import com.example.common.getViewModel
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_main.add_button
import kotlinx.android.synthetic.main.activity_main.new_item_text_input
import kotlinx.android.synthetic.main.activity_main.next_button
import kotlinx.android.synthetic.main.activity_main.output_label
import kotlinx.android.synthetic.main.activity_main.remove_button
import kotlinx.android.synthetic.main.activity_main.previous_button

class MainActivityView : AppCompatActivity() {
    @Inject internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel : MainActivityViewModel

    companion object {
        private val sMainActivityComponent = DaggerMainActivityComponent.builder()
            .appModule(HwApplication.mainAppModule).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sMainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = mViewModelFactory.getViewModel(this)

        mViewModel.currentText.observe(this, { onCurrentTextChanged(it) })
        mViewModel.hasPreviousItem.observe(this, { onHasPreviousItemChanged(it) })
        mViewModel.hasNextItem.observe(this, { onHasNextItemChanged(it) })

        add_button.setOnClickListener { mViewModel.addItem(new_item_text_input.text.toString()) }
        remove_button.setOnClickListener { mViewModel.removeItem() }
        previous_button.setOnClickListener { mViewModel.selectPrevious() }
        next_button.setOnClickListener { mViewModel.selectNext() }

        lifecycle.addObserver(mViewModel)
    }

    // Data change events

    private fun onCurrentTextChanged(text: String) {
        output_label.text = text
    }

    private fun onHasPreviousItemChanged(has: Boolean) {
        previous_button.isEnabled = has
    }

    private fun onHasNextItemChanged(has: Boolean) {
        next_button.isEnabled = has
    }
}
