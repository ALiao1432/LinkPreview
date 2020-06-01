package study.ian.linkpreviewer

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Attributes
import org.jsoup.nodes.Document

interface OnOpenGraphCompleteListener {
    fun onOpenGraphComplete(openGraph: OpenGraph?)
    fun onOpenGraphError(e: java.lang.Exception)
}

fun String.getOpenGraphByThread(listener: OnOpenGraphCompleteListener) {
    // https://youtu.be/9ubtCpn1MbE

    Thread {
        lateinit var document: Document

        try {
            document = Jsoup.connect(this).get()
            val cssSelector = "* > meta"
            val metaElements = document.select(cssSelector)

            val contentKey = "content"
            val openGraph = OpenGraph()
            metaElements.map { it.attributes() }
                .forEach { attr ->
                    val type = attr.containOpenGraphAttributeType()
                    type?.let {
                        val key = attr.get(type.attrType)
                        val value = attr.get(contentKey)
                        openGraph.openGraphContentMap.putIfAbsent(key, value)
                    }
                }
            listener.onOpenGraphComplete(openGraph)
        } catch (e: Exception) {
            Log.e("get open graph object error", e.message, e)
            listener.onOpenGraphError(e)
        }
    }.start()
}

private fun Attributes.containOpenGraphAttributeType(): OpenGraph.AttributeType? {
    val types = OpenGraph.AttributeType.values()
    types.forEach {
        if (this.hasKey(it.attrType)) {
            return it
        }
    }
    return null
}

suspend fun String.getOpenGraphByCoroutine(): OpenGraph? {
    return withContext(Dispatchers.IO) {
        lateinit var document: Document

        try {
            document = Jsoup.connect(this@getOpenGraphByCoroutine).get()
        } catch (e: IllegalArgumentException) {
            Log.e("get open graph object error", e.message, e)
            return@withContext null
        }

        val cssSelector = "* > meta"
        val metaElements = document.select(cssSelector)

        val contentKey = "content"
        val openGraph = OpenGraph()
        metaElements.map { it.attributes() }
            .forEach { attr ->
                val type = attr.containOpenGraphAttributeType()
                type?.let {
                    val key = attr.get(type.attrType)
                    val value = attr.get(contentKey)
                    openGraph.openGraphContentMap.putIfAbsent(key, value)
                }
            }
        openGraph
    }
}
