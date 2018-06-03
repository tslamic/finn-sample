package me.tadej.finn.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.AdsRepository
import me.tadej.finn.repo.FavouritesRepository
import me.tadej.finn.repo.Response
import javax.inject.Inject

class AdsViewModel(
    adsRepo: AdsRepository,
    private val favouritesRepo: FavouritesRepository
) : ViewModel(), AdsRepository, FavouritesRepository {
  private val ads = adsRepo.all()
  private val favourites = favouritesRepo.favourites()

  override fun favourites(page: Int): LiveData<List<Ad>> = favourites
  override fun all(page: Int): LiveData<Response<List<Ad>>> = ads
  override fun add(ad: Ad) = favouritesRepo.add(ad)
  override fun remove(ad: Ad) = favouritesRepo.remove(ad)

  class Factory @Inject constructor(
      private val adsRepo: AdsRepository,
      private val favouritesRepo: FavouritesRepository
  ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // As per the guidelines.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AdsViewModel(adsRepo, favouritesRepo) as T
  }
}
