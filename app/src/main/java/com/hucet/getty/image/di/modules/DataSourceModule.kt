package com.hucet.getty.image.di.modules

import com.hucet.getty.image.parser.GettyImageFetcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-23.
 */
@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideGettyImageFetcher(): GettyImageFetcher =
            GettyImageFetcher()
}