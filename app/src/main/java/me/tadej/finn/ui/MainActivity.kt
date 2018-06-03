package me.tadej.finn.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main.*
import me.tadej.finn.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val actionBar = supportActionBar!!
    actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
    actionBar.setDisplayHomeAsUpEnabled(true)

    navView.setNavigationItemSelectedListener(this)

    val fragment = supportFragmentManager.findFragmentById(R.id.content)
    if (fragment == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.content, MainFragment(), MainFragment.TAG)
          .commit()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean =
      when (item.itemId) {
        android.R.id.home -> {
          drawer.openDrawer(GravityCompat.START)
          true
        }
        else -> super.onOptionsItemSelected(item)
      }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    drawer.closeDrawers()
    return when (item.itemId) {
      R.id.menu_main -> {
        replace(MainFragment.TAG, {
          MainFragment()
        })
        true
      }
      R.id.menu_favourites -> {
        replace(FavouritesFragment.TAG, {
          FavouritesFragment()
        })
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun replace(tag: String, create: () -> Fragment) {
    val fragment = supportFragmentManager.findFragmentByTag(tag) ?: create()
    supportFragmentManager.beginTransaction()
        .replace(R.id.content, fragment, tag)
        .commit()
  }
}
