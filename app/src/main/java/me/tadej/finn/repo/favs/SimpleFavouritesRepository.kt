package me.tadej.finn.repo.favs

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.FavouritesRepository
import me.tadej.finn.repo.factory.SimpleAd
import javax.inject.Inject

class SimpleFavouritesRepository @Inject constructor(
    database: FavouritesDatabase
) : FavouritesRepository {
  private val dao = database.dao()

  override fun favourites(page: Int): LiveData<List<Ad>> =
      Transformations.map(dao.favourites(), { ads ->
        ads.map { it as Ad }.toList()
      })

  override fun isFavourite(ad: Ad): Boolean = dao.isFavourite(ad.id()) > 0

  override fun add(ad: Ad) {
    dao.add(ad as SimpleAd)
  }

  override fun remove(ad: Ad) {
    dao.remove(ad as SimpleAd)
  }
}
