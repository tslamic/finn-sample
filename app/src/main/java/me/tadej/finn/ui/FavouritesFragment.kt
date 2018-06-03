package me.tadej.finn.ui

import android.arch.lifecycle.Observer
import android.view.View
import me.tadej.finn.R
import me.tadej.finn.model.Ad

class FavouritesFragment : AdsFragment() {
  override fun onViewCreated(view: View, model: AdsViewModel, adapter: AdsAdapter) {
    showProgress(true)
    model.favourites().observe(this, Observer<List<Ad>> {
      if (it != null) {
        adapter.ads(it)
        adapter.favourites(it)
      }
      showProgress(false)
    })
  }

  override fun title(): Int = R.string.favourites

  override fun onRefresh(model: AdsViewModel) {
    model.favourites().observe(this, Observer { showProgress(false) })
  }

  companion object {
    const val TAG = "FavouritesFragment"
  }
}
