package com.hucet.getty.image

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.hucet.getty.image.debug.OptionalTree
import com.hucet.getty.image.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import java.lang.reflect.InvocationTargetException
import javax.inject.Inject

/**
 * Created by taesu on 2017-12-23.
 */
class GettyApplication : Application(), HasActivityInjector {


    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initStrictMode()
        initStetho()
    }


    private fun initDagger() {
        AppInjector.init(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(OptionalTree(threadName = true))
        }
    }

    private fun initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build())
        }
    }

    open fun initStetho() {
        if (BuildConfig.DEBUG) {
            try {
                val stethoClazz = Class.forName("com.facebook.stetho.Stetho")
                val method = stethoClazz.getMethod("initializeWithDefaults", Context::class.java)
                method.invoke(null, this)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
        }
    }

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

}