package study.ian.linkpreviewer

import org.jsoup.Jsoup

fun String.getHtml() {
    val document = Jsoup.connect(this).get()

}