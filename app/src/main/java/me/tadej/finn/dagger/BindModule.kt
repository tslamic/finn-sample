@file:Suppress("unused")

package me.tadej.finn.dagger

import dagger.Binds
import dagger.Module
import me.tadej.finn.repo.AdFactory
import me.tadej.finn.repo.AdsRepository
import me.tadej.finn.repo.factory.SimpleAdFactory
import me.tadej.finn.repo.json.JsonParser
import me.tadej.finn.repo.json.SimpleJsonParser
import me.tadej.finn.repo.network.SimpleAdsRepository
import me.tadej.finn.ui.pojo.AdsFormatter
import me.tadej.finn.ui.pojo.SimpleAdsFormatter

@Module
interface BindModule {
  @Binds fun providesAdFactory(factor: SimpleAdFactory): AdFactory
  @Binds fun providesJsonParser(parser: SimpleJsonParser): JsonParser
  @Binds fun providesAdsRepository(repo: SimpleAdsRepository): AdsRepository
  @Binds fun providesFormatter(formatter: SimpleAdsFormatter): AdsFormatter
}
