package study.ian.linkpreview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import study.ian.linkpreviewer.getOpenGraph

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getTestDocument()
    }

    private fun getTestDocument() = runBlocking {
        launch {
            val result = "https://www.youtube.com/watch?v=b8HVQtIoBYU".getOpenGraph()
            result?.let {
                textView.text = "${it.isValid()}"
            }
        }
    }
}
