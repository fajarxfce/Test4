package com.fajarxfce.test4

import android.app.Application
import android.content.pm.ApplicationInfo
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Test4Application : Application() {
    override fun onCreate() {
        super.onCreate()
        if (isDebuggable()){
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun isDebuggable(): Boolean {
        return 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
    }


}


