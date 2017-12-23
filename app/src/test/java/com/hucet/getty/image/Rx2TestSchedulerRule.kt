package com.hucet.getty.image

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.ExternalResource

/**
 * Created by taesu on 2017-12-23.
 */
class Rx2TestSchedulerRule(private val scheduler: TestScheduler) : ExternalResource() {

    override public fun before() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> scheduler })
        RxJavaPlugins.setInitComputationSchedulerHandler({ _ -> scheduler })
        RxJavaPlugins.setComputationSchedulerHandler({ _ -> scheduler })
        RxJavaPlugins.setNewThreadSchedulerHandler({ _ -> scheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ _ -> scheduler })
        RxAndroidPlugins.setMainThreadSchedulerHandler({ _ -> scheduler })
    }

    override fun after() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}