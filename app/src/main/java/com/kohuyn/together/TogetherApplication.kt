package com.kohuyn.together

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.kohuyn.together.di.togetherModule
import com.utils.LogUtil
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class TogetherApplication:MultiDexApplication() {
    private val calligraphyConfig:CalligraphyConfig by inject()
    companion object{
        lateinit var togetherApplication: TogetherApplication

        @JvmStatic
        @Synchronized
        fun getInstance():TogetherApplication{
            return  togetherApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        togetherApplication = this
        startKoin {
            androidContext(this@TogetherApplication)
            modules(togetherModule)
            logger(EmptyLogger())
        }
        LogUtil.init(BuildConfig.DEBUG)
        CalligraphyConfig.initDefault(calligraphyConfig)
    }
}