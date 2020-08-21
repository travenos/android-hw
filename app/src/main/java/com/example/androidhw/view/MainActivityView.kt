package com.example.androidhw.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.androidhw.MainActivityViewModel
import com.example.androidhw.R
import com.example.androidhw.di.DaggerMainActivityComponent
import com.example.common.HwApplication
import com.example.common.getViewModel
import javax.inject.Inject

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
    }

    private fun onCurrentTextChanged(text: String) {
        val textView = findViewById<TextView>(R.id.output_label)
        textView.text = text
    }

    private fun onHasPreviousItemChanged(has: Boolean) {
        val button = findViewById<TextView>(R.id.previous_button)
        button.isEnabled = has
    }

    private fun onHasNextItemChanged(has: Boolean) {
        val button = findViewById<TextView>(R.id.next_button)
        button.isEnabled = has
    }
}
