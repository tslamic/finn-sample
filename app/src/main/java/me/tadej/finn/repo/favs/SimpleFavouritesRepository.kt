package me.tadej.finn.repo.favs

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.FavouritesRepository
import me.tadej.finn.repo.factory.SimpleAd
import java.util.concurrent.Executor
import javax.inject.Inject

/*
 * A simple wrapper around FavouritesDatabase & FavouritesDao.
 *
 * Because we're trying to work with a generic Ad,
 * some not-so-pleasing code must be added to make both Room and
 * FavouritesRepository happy.
 */
class SimpleFavouritesRepository @Inject constructor(
    private val executor: Executor,
    database: FavouritesDatabase
) : FavouritesRepository {
  private val dao = database.dao()

  override fun favourites(page: Int): LiveData<List<Ad>> =
      Transformations.map(dao.favourites(), { ads ->
        ads.map { it as Ad }.toList()
      })

  override fun add(ad: Ad) = executor.execute {
    dao.add(ad as SimpleAd)
  }

  override fun remove(ad: Ad) = executor.execute {
    dao.remove(ad as SimpleAd)
  }
}
