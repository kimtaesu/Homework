package com.hucet.getty.image.model

/**
 * Created by taesu on 2017-12-23.
 */
data class GettyImage(
        private val _url: String,
        val caption: String
) {
    var url = _url
        get() = "http://www.gettyimagesgallery.com/$_url"
}