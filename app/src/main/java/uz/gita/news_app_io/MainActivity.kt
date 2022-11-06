package uz.gita.news_app_io

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.news_app_io.presentation.dialogs.ProgressDialog

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = ProgressDialog(this)
    }

    fun showProgress() {
        dialog.show()
    }


    fun hideProgress() {
        dialog.cancel()
    }
}