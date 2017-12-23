package com.hucet.getty.image.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import com.hucet.getty.image.Rx2TestSchedulerRule
import com.hucet.getty.image.TestApplication
import com.hucet.getty.image.model.GettyImage
import com.hucet.getty.image.parser.GettyImageFetcher
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.amshove.kluent.any
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Rule
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit


/**
 * Created by taesu on 2017-12-23.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), application = TestApplication::class)
class GettyViewModelTest {
    private lateinit var viewModel: GettyViewModel
    @Mock lateinit var fetcher: GettyImageFetcher
    @Mock private lateinit var observer: Observer<List<GettyImage>>
    @Mock private lateinit var errorObser: Observer<Throwable>
    private val testScheduler = TestScheduler()


    @get:Rule
    var rx2TestSchedulerRule = Rx2TestSchedulerRule(testScheduler)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(fetcher.getImages()).thenReturn(Single.just(listOf()))
        viewModel = GettyViewModel(fetcher)
        viewModel.imagesLiveData().observeForever(observer)
        viewModel.errorLiveData().observeForever(errorObser)
    }

    @Test
    fun imagesLiveDataPostValue() {

        viewModel.requestFetch()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(observer, times(1)).onChanged(any())
        verify(errorObser, never()).onChanged(any())
    }

    @Ignore
    @Test
    fun errorFetch() {
        whenever(fetcher.getImages()).thenThrow(RuntimeException("Hello"))

        viewModel.requestFetch()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(observer, never()).onChanged(any())
        verify(errorObser, times(1)).onChanged(any())
    }
}