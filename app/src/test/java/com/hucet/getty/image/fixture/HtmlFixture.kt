package com.hucet.getty.image.fixture

/**
 * Created by taesu on 2017-12-23.
 */
object HtmlFixture {

    fun get(path: String): String {
        return parseRes(path)
    }

    private fun parseRes(path: String): String {
        return this.javaClass.classLoader.getResource(path).readText()
    }
}