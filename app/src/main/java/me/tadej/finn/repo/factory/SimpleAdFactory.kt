package me.tadej.finn.repo.factory

import me.tadej.finn.model.Ad
import me.tadej.finn.repo.AdFactory
import javax.inject.Inject

class SimpleAdFactory @Inject constructor() : AdFactory {
  override fun create(
      id: String,
      image: String,
      price: Long,
      location: String,
      description: String
  ): Ad {
    return SimpleAd(id, image, price, location, description)
  }
}
