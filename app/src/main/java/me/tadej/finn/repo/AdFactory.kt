package me.tadej.finn.repo

import me.tadej.finn.model.Ad

interface AdFactory {
  fun create(
      id: String,
      image: String,
      price: Long,
      location: String,
      description: String,
      json: String
  ): Ad
}
