package me.tadej.finn.repo

import android.arch.lifecycle.LiveData
import me.tadej.finn.model.Ad

interface FavouritesRepository {
  fun favourites(page: Int = 1): LiveData<List<Ad>>
  fun isFavourite(ad: Ad): Boolean
  fun add(ad: Ad)
  fun remove(ad: Ad)
}
