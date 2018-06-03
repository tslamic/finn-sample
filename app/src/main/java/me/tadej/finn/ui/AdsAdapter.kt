package me.tadej.finn.ui

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import me.tadej.finn.R
import me.tadej.finn.model.Ad
import me.tadej.finn.ui.pojo.AdsFormatter
import me.tadej.finn.ui.pojo.OnFavouriteClickListener

class AdsAdapter(
    private val listener: OnFavouriteClickListener,
    private val formatter: AdsFormatter
) : RecyclerView.Adapter<AdsViewHolder>() {

  private var ads = emptyList<Ad>()
  private var favourites = emptySet<Ad>()
  private val cache = HashMap<Ad, Int>()

  override fun getItemCount(): Int = ads.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
    val context = parent.context
    val view = LayoutInflater.from(context).inflate(R.layout.view_ad, parent, false)
    return AdsViewHolder(view)
  }

  override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
    val ad = ads[position]

    val url = formatter.formatUrl(ad)
    Glide.with(holder.itemView).load(url).into(object : SimpleTarget<Drawable>() {
      override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        holder.itemView.background = resource
      }
    })

    holder.favourite.setImageResource(favouriteIcon(ad))
    holder.favourite.setOnClickListener {
      cache[ad] = position
      if (favourites.contains(ad)) {
        listener.remove(ad)
      } else {
        listener.add(ad)
      }
    }

    holder.title.text = ad.description()
    holder.description.text = formatter.formatContent(ad)
  }

  @DrawableRes private fun favouriteIcon(ad: Ad) =
      if (favourites.contains(ad)) {
        R.drawable.ic_favorite_full
      } else {
        R.drawable.ic_favourite_empty
      }

  internal fun ads(ads: List<Ad>) {
    this.ads = ads
    notifyDataSetChanged()
  }

  internal fun favourites(ads: List<Ad>) {
    for ((_, v) in cache) {
      notifyItemChanged(v)
    }
    cache.clear()
    favourites = ads.toSet()
  }
}
