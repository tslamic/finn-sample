package me.tadej.finn.repo.factory

import me.tadej.finn.model.Ad

data class SimpleAd(
    val id: String,
    val imageUrl: String,
    val price: Long,
    val location: String,
    val description: String,
    val json: String
) : Ad {
  override fun id(): String = id
  override fun imageUrl(): String = imageUrl
  override fun price(): Long = price
  override fun location(): String = location
  override fun description(): String = description
}
