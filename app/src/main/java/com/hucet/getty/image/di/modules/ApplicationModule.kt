package com.hucet.getty.image.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-23.
 */
@Module(includes = arrayOf(
        DataSourceModule::class,
        ViewModelModule::class
))
open class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}
