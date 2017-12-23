package com.hucet.getty.image.parser

import com.hucet.getty.image.fixture.HtmlFixture
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test

/**
 * Created by taesu on 2017-12-23.
 */
class GettyImageFetcherTest {

    private val testHtml = HtmlFixture.get("getty.html")

    private lateinit var fetcher: GettyImageFetcher
    @Before
    fun setUp() {
        fetcher = GettyImageFetcher()
    }

    @Test
    fun parseGettyFromHtml() {
        val images = fetcher.parseString(HtmlFixture.get("getty.html"))

        images.size `shouldEqualTo` 391
        images.first().caption `shouldEqualTo` "A Roman Prince"
        images.last().caption `shouldEqualTo` "Zeffirelli At Home"
    }

    @Test
    fun getImagesStateCompleted() {
        fetcher.getImages().test()
                .assertComplete()
    }

    @Test
    fun getImagesStateError() {
        fetcher.getImages().map { throw RuntimeException("Hello") }.test()
                .assertNotComplete()
                .assertError(RuntimeException::class.java)
    }
}