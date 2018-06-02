@file:JvmName("ResourcesUtil")

package me.tadej.finn

fun readResource(resource: String): String {
  val loader = ClassLoader.getSystemClassLoader()
  val stream = loader.getResourceAsStream(resource)
  stream.bufferedReader().use {
    return it.readText()
  }
}

enum class JsonResources(private val resource: String) {
  CORRUPTED("discover-corrupted.json"),
  PARTIAL("discover-partial.json"),
  FULL("discover-full.json");

  fun asJson(): String = readResource(resource)
}
