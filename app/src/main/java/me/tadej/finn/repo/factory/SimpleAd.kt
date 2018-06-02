package me.tadej.finn.repo.factory

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import me.tadej.finn.model.Ad

@Entity(tableName = "favourites")
data class SimpleAd(
    @PrimaryKey val id: String,
    val imageUrl: String?,
    val price: Long = -1L,
    val location: String?,
    val description: String?
) : Ad {
  @Ignore override fun id(): String = id
  @Ignore override fun imageUrl(): String = imageUrl.orEmpty()
  @Ignore override fun price(): Long = price
  @Ignore override fun location(): String = location.orEmpty()
  @Ignore override fun description(): String = description.orEmpty()
}
