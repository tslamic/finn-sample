package me.tadej.finn.dagger

import dagger.Binds
import dagger.Module
import me.tadej.finn.repo.AdFactory
import me.tadej.finn.repo.factory.SimpleAdFactory
import me.tadej.finn.repo.json.JsonParser
import me.tadej.finn.repo.json.SimpleJsonParser

@Module
interface BindModule {
  @Binds fun providesAdFactory(factor: SimpleAdFactory): AdFactory
  @Binds fun providesJsonParser(parser: SimpleJsonParser): JsonParser
}
