package com.hucet.getty.image.di.component

import android.app.Application
import com.hucet.getty.image.GettyApplication
import com.hucet.getty.image.di.modules.ApplicationModule
import com.hucet.getty.image.di.modules.MainAcitivtyModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by taesu on 2017-12-23.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        MainAcitivtyModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GettyApplication)
}
