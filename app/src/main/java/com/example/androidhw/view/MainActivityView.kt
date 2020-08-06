package com.example.androidhw.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.androidhw.MainActivityViewModel
import com.example.androidhw.R
import com.example.androidhw.di.DaggerMainActivityComponent
import com.example.common.getViewModel
import java.io.File
import javax.inject.Inject

class MainActivityView : AppCompatActivity() {
    @Inject internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //settingsComponent.createLicenseInfoComponent().inject(this)
        DaggerMainActivityComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = mViewModelFactory.getViewModel(this)


//        val textView = findViewById<TextView>(R.id.output_label)
//        textView.setText(directoryDownloads)
    }

//    private val directoryDownloads : String by lazy {
//        val downloadsDir : File? = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
//        downloadsDir?.absolutePath ?: ""
//    }
}
