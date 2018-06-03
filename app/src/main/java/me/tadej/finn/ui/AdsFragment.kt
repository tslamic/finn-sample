package me.tadej.finn.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import me.tadej.finn.App
import me.tadej.finn.R
import me.tadej.finn.model.Ad
import me.tadej.finn.ui.pojo.AdsFormatter
import me.tadej.finn.ui.pojo.OnFavouriteClickListener
import javax.inject.Inject

private const val LAYOUT_MANAGER_SPAN_COUNT = 2

abstract class AdsFragment : Fragment(), OnFavouriteClickListener {
  @Inject protected lateinit var factory: AdsViewModel.Factory
  @Inject protected lateinit var formatter: AdsFormatter

  private lateinit var model: AdsViewModel

  final override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val component = (context!!.applicationContext as App).component
    component.inject(this)
  }

  final override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  final override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    activity!!.setTitle(title())
  }

  final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    model = ViewModelProviders.of(this, factory)
        .get(AdsViewModel::class.java)

    swiper.setOnRefreshListener { onRefresh(model) }
    val adapter = AdsAdapter(this, formatter)
    val manager = GridLayoutManager(context, LAYOUT_MANAGER_SPAN_COUNT)
    recycler.adapter = adapter
    recycler.layoutManager = manager

    onViewCreated(view, model, adapter)
  }

  final override fun add(ad: Ad) = model.add(ad)
  final override fun remove(ad: Ad) = model.remove(ad)

  protected fun showProgress(show: Boolean) {
    swiper.isRefreshing = show
  }

  protected abstract fun onViewCreated(view: View, model: AdsViewModel, adapter: AdsAdapter)
  protected abstract fun onRefresh(model: AdsViewModel)
  @StringRes protected abstract fun title(): Int
}
