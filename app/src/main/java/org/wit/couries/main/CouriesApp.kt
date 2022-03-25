package org.wit.couries.main

import android.app.Application
import timber.log.Timber

class CouriesApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("DonationX Application Started")

    }
}