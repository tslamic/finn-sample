package me.tadej.finn.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import me.tadej.finn.R
import me.tadej.finn.model.Ad
import me.tadej.finn.repo.Response
import me.tadej.finn.ui.BaseFragment


class MainFragment : BaseFragment() {
  override fun layoutRes(): Int = R.layout.fragment_main

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val model = ViewModelProviders.of(this).get(MainViewModel::class.java)
    model.ads().observe(this, Observer<Response<List<Ad>>> {})
  }
}
