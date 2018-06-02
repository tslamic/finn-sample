package me.tadej.finn.repo.favs

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import me.tadej.finn.repo.factory.SimpleAd

@Database(entities = [(SimpleAd::class)], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {
  abstract fun dao(): FavouritesDao
}
