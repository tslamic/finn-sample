package me.tadej.finn.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.view_ad.view.*

class AdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val title: TextView = view.adTitle
  val description: TextView = view.adDescription
  val favourite: ImageView = view.adFavourite
}
