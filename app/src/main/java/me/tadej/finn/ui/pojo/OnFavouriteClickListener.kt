package me.tadej.finn.ui.pojo

import me.tadej.finn.model.Ad

interface OnFavouriteClickListener {
  fun add(ad: Ad)
  fun remove(ad: Ad)
}
