package me.tadej.finn.ui

import android.arch.lifecycle.Observer
import android.support.design.widget.Snackbar
import android.view.View
import me.tadej.finn.R
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.Response

class MainFragment : AdsFragment() {
  override fun onViewCreated(view: View, model: AdsViewModel, adapter: AdsAdapter) {
    showProgress(true)
    model.all().observe(this, Observer<Response<List<Ad>>> {
      if (it != null) handleAds(view, adapter, it)
      showProgress(false)
    })

    model.favourites().observe(this, Observer<List<Ad>> {
      if (it != null) adapter.favourites(it)
    })
  }

  override fun onRefresh(model: AdsViewModel) {
    model.all().observe(this, Observer { showProgress(false) })
  }

  override fun title(): Int = R.string.ads

  private fun handleAds(view: View, adapter: AdsAdapter, resp: Response<List<Ad>>) {
    if (resp.isSuccessful()) {
      val ads = resp.response()
      adapter.ads(ads)
    } else {
      val message = resp.error().message ?: getString(R.string.error)
      Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
  }

  companion object {
    const val TAG = "MainFragment"
  }
}
