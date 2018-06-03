package me.tadej.finn.ui.pojo

import me.tadej.finn.model.Ad
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class SimpleAdsFormatter @Inject constructor() : AdsFormatter {
  private val formatter = NumberFormat.getInstance(Locale("NB"))

  override fun formatUrl(ad: Ad): String {
    return "https://images.finncdn.no/dynamic/480x360c/" + ad.imageUrl()
  }

  override fun formatPrice(ad: Ad): String {
    val price = ad.price()
    return formatter.format(price)
  }

  override fun formatContent(ad: Ad): String {
    val location = ad.location()
    val price = formatPrice(ad)
    return "$location, $price"
  }
}
