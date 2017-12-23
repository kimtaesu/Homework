package com.hucet.getty.image.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hucet.getty.image.model.GettyImage
import com.hucet.getty.image.parser.GettyImageFetcher
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-23.
 */
@Singleton
class GettyViewModel @Inject constructor(
        private val fetcher: GettyImageFetcher
) : ViewModel() {
    private val imagesLiveData = MutableLiveData<List<GettyImage>>()
    private val errorLiveData = MutableLiveData<Throwable>()
    fun requestFetch() {
        fetcher.getImages()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    imagesLiveData.postValue(it)
                }, {
                    Timber.e(it)
                    errorLiveData.postValue(it)
                })
    }

    fun imagesLiveData() = imagesLiveData
    fun errorLiveData() = errorLiveData

    override fun onCleared() {
        super.onCleared()
    }
}