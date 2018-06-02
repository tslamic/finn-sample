package me.tadej.finn

import android.app.Application
import me.tadej.finn.dagger.AppComponent
import me.tadej.finn.dagger.AppModule
import me.tadej.finn.dagger.DaggerAppComponent

class App : Application() {
  private lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()
    component = DaggerAppComponent.builder()
        .appModule(AppModule(applicationContext))
        .build()
  }
}
