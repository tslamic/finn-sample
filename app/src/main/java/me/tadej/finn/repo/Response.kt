package me.tadej.finn.repo

class Response<T> private constructor(
    private val response: T?,
    private val error: Throwable?
) {
  companion object {
    fun <T> success(data: T): Response<T> = Response(data, null)
    fun <T> failure(throwable: Throwable): Response<T> = Response(null, throwable)
    fun <T> failure(message: String): Response<T> = Response(null, Throwable(message))
  }

  fun response(): T = response!!
  fun error(): Throwable = error!!
  fun isSuccessful() = error == null
}
