package me.tadej.finn.repo.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.AdsRepository
import me.tadej.finn.repo.Response
import me.tadej.finn.repo.json.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.Executor
import javax.inject.Inject

private const val URL =
    "https://gist.githubusercontent.com/3lvis/3799feea005ed49942dcb56386ecec2b/raw/63249144485884d279d55f4f3907e37098f55c74/discover.json"

class SimpleAdsRepository @Inject constructor(
    private val executor: Executor,
    private val parser: JsonParser,
    private val client: OkHttpClient
) : AdsRepository {
  private val data = MutableLiveData<Response<List<Ad>>>()

  override fun all(page: Int): LiveData<Response<List<Ad>>> {
    executor.execute {
      val response = request()
      data.postValue(response)
    }
    return data
  }

  private fun request(): Response<List<Ad>> {
    val request = Request.Builder()
        .get()
        .url(URL)
        .build()
    return try {
      client.newCall(request).execute().use { resp ->
        if (resp.isSuccessful) {
          val json = resp.bodyAsString()
          val ads = parser.parse(json)
          Response.success(ads)
        } else {
          val message = resp.bodyAsString()
          Response.failure(message)
        }
      }
    } catch (ex: Exception) {
      Response.failure(ex)
    }
  }

  private fun okhttp3.Response.bodyAsString() = this.body()?.string() ?: ""
}
