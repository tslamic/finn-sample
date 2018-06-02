package me.tadej.finn.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (BindModule::class)])
interface AppComponent
