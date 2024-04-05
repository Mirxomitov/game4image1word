package uz.gita.fourpiconewordkotlin.app

import android.app.Application
import uz.gita.fourpiconewordkotlin.data.source.MyShared
import uz.gita.fourpiconewordkotlin.domain.AppController

class App : Application() {
    override fun onCreate() {

        MyShared.init(this)
        AppController.init()
        super.onCreate()
    }
}