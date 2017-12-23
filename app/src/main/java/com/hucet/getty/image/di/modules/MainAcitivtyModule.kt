package com.hucet.getty.image.di.modules

import com.hucet.getty.image.view.MainActivity
import com.hucet.getty.image.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by taesu on 2017-12-23.
 */
@Module
abstract class MainAcitivtyModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(
    ))
    abstract fun bindMainActivity(): MainActivity


}