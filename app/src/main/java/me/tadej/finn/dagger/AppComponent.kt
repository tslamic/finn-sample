package me.tadej.finn.dagger

import dagger.Component
import me.tadej.finn.ui.AdsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (BindModule::class)])
interface AppComponent {
  fun inject(fragment: AdsFragment)
}
