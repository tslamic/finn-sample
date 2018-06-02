package me.tadej.finn.repo

import android.arch.lifecycle.LiveData
import me.tadej.finn.model.Ad

interface AdsRepository {
  fun all(page: Int = 1): LiveData<Response<List<Ad>>>
}
