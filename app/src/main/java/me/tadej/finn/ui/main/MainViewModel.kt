package me.tadej.finn.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.AdsRepository
import me.tadej.finn.repo.Response
import me.tadej.finn.repo.factory.SimpleAdFactory
import me.tadej.finn.repo.json.SimpleJsonParser
import me.tadej.finn.repo.network.SimpleAdsRepository
import okhttp3.OkHttpClient
import java.util.concurrent.Executors

class MainViewModel constructor(
    repo: AdsRepository = SimpleAdsRepository(
        Executors.newSingleThreadExecutor(),
        SimpleJsonParser(SimpleAdFactory),
        OkHttpClient.Builder().build()
    )
) : ViewModel() {
  private val data: LiveData<Response<List<Ad>>> = repo.all()

  fun ads(): LiveData<Response<List<Ad>>> {
    return data
  }
}
