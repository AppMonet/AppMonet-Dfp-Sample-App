package com.monet.sample.dfp

import android.app.Application
import com.monet.bidder.AppMonet
import com.monet.bidder.AppMonetConfiguration

class AppMonetApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //AppMonet initialization.
        val appMonetConfiguration = AppMonetConfiguration.Builder()
            .applicationId(BuildConfig.APP_ID)
            .build()
        AppMonet.init(this, appMonetConfiguration)
    }
}