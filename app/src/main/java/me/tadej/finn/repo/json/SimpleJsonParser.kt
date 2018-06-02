package me.tadej.finn.repo.json

import me.tadej.finn.model.Ad
import me.tadej.finn.repo.AdFactory
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

private const val KEY_ITEMS = "items"
private const val KEY_ID = "id"
private const val KEY_IMAGE = "image"
private const val KEY_URL = "url"
private const val KEY_PRICE = "price"
private const val KEY_VALUE = "value"
private const val KEY_LOCATION = "location"
private const val KEY_DESCRIPTION = "description"

class SimpleJsonParser @Inject constructor(
    private val factory: AdFactory
) : JsonParser {
  override fun parse(json: String?): List<Ad> {
    if (json.isNullOrEmpty()) {
      return emptyList()
    }

    val content = JSONObject(json)
    val items = content.getJSONArray(KEY_ITEMS)
    if (items.isNullOrEmpty()) {
      return emptyList()
    }

    return collect(items!!)
  }

  private fun collect(items: JSONArray): List<Ad> {
    val length = items.length()

    val ads = ArrayList<Ad>(length)
    for (i in 0 until length) {
      val obj = items.getJSONObject(i)
      if (obj.isNullOrEmpty()) {
        continue
      }
      val ad = create(obj)
      ads.add(ad)
    }

    return ads
  }

  private fun create(jsonObject: JSONObject): Ad {
    val id = jsonObject.getString(KEY_ID)
    val image = jsonObject.optJSONObject(KEY_IMAGE)?.optString(KEY_URL) ?: ""
    val price = jsonObject.optJSONObject(KEY_PRICE)?.optLong(KEY_VALUE) ?: 0L
    val location = jsonObject.optString(KEY_LOCATION)
    val description = jsonObject.optString(KEY_DESCRIPTION)
    return factory.create(id, image, price, location, description)
  }

  private fun JSONArray?.isNullOrEmpty(): Boolean {
    return this == null || length() == 0
  }

  private fun JSONObject?.isNullOrEmpty(): Boolean {
    return this == null || length() == 0
  }
}
