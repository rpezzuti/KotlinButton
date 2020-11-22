package rhett.pezzuti.kotlinbutton

import android.app.Application
import timber.log.Timber

class ButtonApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}