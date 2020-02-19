package study.ian.linkpreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import study.ian.linkpreviewer.getHtml

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "https://www.youtube.com/watch?v=b8HVQtIoBYU".getHtml()
    }
}
