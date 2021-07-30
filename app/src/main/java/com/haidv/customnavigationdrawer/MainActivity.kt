package com.haidv.customnavigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.haidv.customnavigationdrawer.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerLayout: DrawerLayout? = null
    private var toolBar: Toolbar? = null
    private var navigationView: NavigationView? = null

    private val FRAGMENT_HOME = 0
    private val FRAGMENT_FAVORITE = 1
    private val FRAGMENT_HISTORY = 2
    private val FRAGMENT_PROFILE = 3
    private val FRAGMENT_CHANGE_PASS = 4

    private var currentFragment = FRAGMENT_HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        toolBar = findViewById(R.id.toolBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        setSupportActionBar(toolBar)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout?.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView?.setNavigationItemSelectedListener(this)
        replaceFragment(HomeFragment.newInstance())
        navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuHome) {
            if (currentFragment != FRAGMENT_HOME) {
                replaceFragment(HomeFragment.newInstance())
                currentFragment = FRAGMENT_HOME
            }
        } else if (item.itemId == R.id.menuFavorite) {
            if (currentFragment != FRAGMENT_FAVORITE) {
                replaceFragment(FavoriteFragment.newInstance())
                currentFragment = FRAGMENT_FAVORITE
            }
        } else if (item.itemId == R.id.menuHistory) {
            if (currentFragment != FRAGMENT_HISTORY) {
                replaceFragment(HistoryFragment.newInstance())
                currentFragment = FRAGMENT_HISTORY
            }
        } else if (item.itemId == R.id.menuProfile) {
            if (currentFragment != FRAGMENT_PROFILE) {
                replaceFragment(ProfileFragment.newInstance())
                currentFragment = FRAGMENT_PROFILE
            }
        } else if (item.itemId == R.id.menuChangePass) {
            if (currentFragment != FRAGMENT_CHANGE_PASS) {
                replaceFragment(ChangePassWordFragment.newInstance())
                currentFragment = FRAGMENT_CHANGE_PASS
            }
        }
        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }
}