package com.example.androidhw.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.androidhw.MainActivityViewModel
import com.example.androidhw.R
import com.example.androidhw.di.DaggerMainActivityComponent
import com.example.common.getViewModel
import javax.inject.Inject

class MainActivityView : AppCompatActivity() {
    @Inject internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainActivityComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = mViewModelFactory.getViewModel(this)

        mViewModel.currentText.observe(this, Observer { onCurrentTextChanged(it) })


//        val textView = findViewById<TextView>(R.id.output_label)
//        textView.setText(directoryDownloads)
    }

    private fun onCurrentTextChanged(text: String) {
        //TODO!!!
    }

//    private val directoryDownloads : String by lazy {
//        val downloadsDir : File? = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
//        downloadsDir?.absolutePath ?: ""
//    }
}
