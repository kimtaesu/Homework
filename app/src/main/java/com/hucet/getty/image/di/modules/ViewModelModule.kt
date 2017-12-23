package com.hucet.getty.image.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.hucet.getty.image.viewmodel.GettyViewModel
import com.hucet.getty.image.viewmodel.GettyViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Created by taesu on 2017-12-23.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GettyViewModel::class)
    abstract fun bindGettyViewModel(beerViewModel: GettyViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GettyViewModelFactory): ViewModelProvider.Factory


    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}