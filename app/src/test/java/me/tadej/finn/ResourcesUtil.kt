@file:JvmName("ResourcesUtil")

package me.tadej.finn

fun readResource(resource: String): String {
  val loader = ClassLoader.getSystemClassLoader()
  val stream = loader.getResourceAsStream(resource)
  stream.bufferedReader().use {
    return it.readText()
  }
}
