package com.hucet.getty.image.debug

import timber.log.Timber

/**
 * Created by taesu on 2017-10-30.
 */
class OptionalTree(val threadName: Boolean = false) : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        var msg = message
        if (threadName)
            msg = "Thread[${Thread.currentThread().name}] ${message}"
        super.log(priority, tag, msg, t)
    }
}