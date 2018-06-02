package me.tadej.finn.misc

import me.tadej.finn.model.Ad

interface ImageUrlGenerator {
  fun generateUrl(ad: Ad): String
}
