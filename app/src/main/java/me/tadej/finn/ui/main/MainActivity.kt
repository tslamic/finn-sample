package me.tadej.finn.ui.main

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.tadej.finn.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val actionBar = supportActionBar!!
    actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
    actionBar.setDisplayHomeAsUpEnabled(true)

    navView.setNavigationItemSelectedListener {
      it.isChecked = true
      drawer.closeDrawers()
      true
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    android.R.id.home -> {
      drawer.openDrawer(GravityCompat.START)
      true
    }
    else -> super.onOptionsItemSelected(item)
  }

  override fun onSupportNavigateUp() =
      findNavController(this, R.id.navigator).navigateUp()
}
