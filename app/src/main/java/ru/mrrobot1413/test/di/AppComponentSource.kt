package ru.mrrobot1413.test.di

class AppComponentSource {
    companion object{
        lateinit var appComponentSource: AppComponent

        fun setAppComponent(appComponent: AppComponent){
            this.appComponentSource = appComponent
        }
    }
}