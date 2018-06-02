package me.tadej.finn.repo.factory

import me.tadej.finn.model.Ad
import me.tadej.finn.repo.AdFactory

object SimpleAdFactory : AdFactory {
  override fun create(
      id: String,
      image: String,
      price: Long,
      location: String,
      description: String,
      json: String
  ): Ad {
    return SimpleAd(id, image, price, location, description, json)
  }
}
