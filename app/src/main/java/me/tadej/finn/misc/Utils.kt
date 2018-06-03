@file:JvmName("Utils")

package me.tadej.finn.misc

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo.FLAG_LARGE_HEAP

/**
 * Returns a bit more than 5% of the available memory resources on a device.
 *
 * Shamelessly stolen from Picasso library:
 *   https://github.com/square/picasso/blob/master/picasso/src/main/java/com/squareup/picasso3/Utils.java
 */
fun getCacheSize(context: Context): Long {
  val am = context.getSystemService(ActivityManager::class.java) as ActivityManager
  val largeHeap = (context.applicationInfo.flags.and(FLAG_LARGE_HEAP)) != 0
  val memoryClass = if (largeHeap) am.largeMemoryClass else am.memoryClass
  return 1024L * 1024L * memoryClass.toLong() / 15
}
