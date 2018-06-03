package me.tadej.finn.ui.pojo

import me.tadej.finn.model.Ad

interface AdsFormatter {
  fun formatUrl(ad: Ad): String
  fun formatPrice(ad: Ad): String
  fun formatContent(ad: Ad): String
}
