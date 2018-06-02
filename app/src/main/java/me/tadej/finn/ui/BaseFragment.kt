package me.tadej.finn.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_main.*


abstract class BaseFragment : Fragment() {
  final override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutRes(), container, false)
  }

  @LayoutRes protected abstract fun layoutRes(): Int

  protected fun showProgress(show: Boolean) {
    progress.visibility = if (show) View.VISIBLE else View.GONE
  }
}
