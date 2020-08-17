package com.example.androidhw.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
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
            .appModule(HwApplication.MainAppModule).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sMainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = mViewModelFactory.getViewModel(this)

        mViewModel.currentText.observe(this, Observer { onCurrentTextChanged(it) })


//        val textView = findViewById<TextView>(R.id.output_label)
//        textView.setText(directoryDownloads)
    }

    private fun onCurrentTextChanged(text: String) {
        val textView = findViewById<TextView>(R.id.output_label)
        textView.text = text
    }

//    private val directoryDownloads : String by lazy {
//        val downloadsDir : File? = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
//        downloadsDir?.absolutePath ?: ""
//    }
}
