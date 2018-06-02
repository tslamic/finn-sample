package me.tadej.finn.model

interface Ad {
  fun id(): String
  fun imageUrl(): String
  fun price(): Long
  fun location(): String
  fun description(): String
}
