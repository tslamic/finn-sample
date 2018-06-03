package me.tadej.finn.repo.favs

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import me.tadej.finn.repo.factory.SimpleAd

@Dao
interface FavouritesDao {
  @Query(value = "SELECT * FROM favourites") fun favourites(): LiveData<List<SimpleAd>>
  @Insert(onConflict = REPLACE) fun add(ad: SimpleAd)
  @Delete() fun remove(ad: SimpleAd)
}
