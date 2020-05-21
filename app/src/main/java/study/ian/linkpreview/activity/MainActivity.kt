package study.ian.linkpreview.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import study.ian.linkpreview.R
import study.ian.linkpreview.databinding.ActivityMainBinding
import study.ian.linkpreviewer.OnOpenGraphCompleteListener
import study.ian.linkpreviewer.OpenGraph
import study.ian.linkpreviewer.getOpenGraphByThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.addButton.setOnClickListener { getOpenGraphByThread(binding.urlInputEdt.text.toString()) }
    }

    private fun getOpenGraphByThread(url: String) {
        if (url.isEmpty()) {
            return
        }
        url.getOpenGraphByThread(object : OnOpenGraphCompleteListener {
            override fun onOpenGraphComplete(openGraph: OpenGraph?) {
                openGraph?.let {
                    runOnUiThread {
                        Glide.with(this@MainActivity)
                            .load(it.getContent(OpenGraph.OpenGraphAttribute.OG_IMAGE))
                            .centerInside()
                            .into(binding.ogLayout.ogImage)
                        binding.ogLayout.ogTitleText.text = it.getContent(OpenGraph.OpenGraphAttribute.OG_TITLE)
                        binding.ogLayout.ogDescriptionText.text = it.getContent(OpenGraph.OpenGraphAttribute.OG_DESCRIPTION)
                        binding.ogLayout.ogUrlText.text = it.getContent(OpenGraph.OpenGraphAttribute.OG_URL)
                    }
                }
            }

            override fun onOpenGraphError(e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
