package com.hucet.getty.image.parser

import android.support.annotation.VisibleForTesting
import com.hucet.getty.image.model.GettyImage
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


/**
 * Created by taesu on 2017-12-23.
 */
class GettyImageFetcher {
    private val GETTY_URL = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx"
    fun getImages(): Single<List<GettyImage>> {
        return Single.defer {
            val html = Jsoup.connect(GETTY_URL).get()
            Single.just(parse(html))
        }
    }

    private fun parse(html: Document): List<GettyImage> {
        val blocks = html.getElementsByClass("gallery-item-group exitemrepeater")
        val result = arrayListOf<GettyImage>()
        for (element in blocks) {
            val url = element.getElementsByClass("picture").attr("src")
            val caption = element.getElementsByClass("gallery-item-caption").select("a").text()
            result.add(GettyImage(url, caption))
        }
        return result
    }

    @VisibleForTesting
    fun parseString(html: String): List<GettyImage> {
        return parse(Jsoup.parse(html))
    }
}