package me.tadej.finn.repo.network

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import me.tadej.finn.JsonResources
import me.tadej.finn.repo.factory.SimpleAdFactory
import me.tadej.finn.repo.json.SimpleJsonParser
import okhttp3.*
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.concurrent.Executor

class SimpleAdsRepositoryTest {
  @Rule @JvmField val instantExecutorRule = InstantTaskExecutorRule()

  private val executor = Executor { runnable -> runnable?.run() }
  private val parser = SimpleJsonParser(SimpleAdFactory())
  private val client = mock(OkHttpClient::class.java)

  private val repository = SimpleAdsRepository(executor, parser, client)

  @Test
  fun all_withPartialJson_assertSuccessfulResponse() {
    val json = JsonResources.PARTIAL.asJson()
    mockResponse(200, "200 OK", json)

    val data = repository.all()

    val resp = data.value!!
    assertThat(resp.isSuccessful()).isTrue()
    assertThat(resp.response()).hasSize(2)
  }

  @Test
  fun all_withPartialJson_assertFailedResponse() {
    val json = JsonResources.PARTIAL.asJson()
    mockResponse(400, "400 Bad Request", json)

    val data = repository.all()

    val resp = data.value!!
    assertThat(resp.isSuccessful()).isFalse()
    assertThat(resp.error()).isNotNull()
  }


  private fun mockResponse(code: Int, message: String, jsonBody: String) {
    val request = Request.Builder()
        .get()
        .url("http://www.example.com")
        .build()

    val type = MediaType.parse("application/json; charset=utf-8")
    val body = ResponseBody.create(type, jsonBody)

    val response = Response.Builder()
        .protocol(Protocol.HTTP_1_1)
        .request(request)
        .code(code)
        .message(message)
        .body(body)
        .build()

    val call = mock(Call::class.java)
    `when`(client.newCall(any(Request::class.java))).thenReturn(call)
    `when`(call.execute()).thenReturn(response)
  }
}
