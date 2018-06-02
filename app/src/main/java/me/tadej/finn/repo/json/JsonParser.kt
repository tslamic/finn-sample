package me.tadej.finn.repo.json

import android.support.annotation.WorkerThread
import me.tadej.finn.model.Ad

interface JsonParser {
  @WorkerThread fun parse(json: String?): List<Ad>
}
