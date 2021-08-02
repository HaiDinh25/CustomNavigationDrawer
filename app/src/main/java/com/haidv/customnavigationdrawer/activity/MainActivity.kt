package com.haidv.customnavigationdrawer.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.haidv.customnavigationdrawer.R
import com.haidv.customnavigationdrawer.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    NavigationBarView.OnItemSelectedListener, View.OnClickListener {

    private var drawerLayout: DrawerLayout? = null
    private var toolBar: Toolbar? = null
    private var navigationView: NavigationView? = null
    private var bottomNavigation: BottomNavigationView? = null

    private var header: View? = null
    private var buttonMenu: View? = null
    private var textViewTitle: TextView? = null

    private val FRAGMENT_HOME = 0
    private val FRAGMENT_ACTIVITY = 1
    private val FRAGMENT_SETTING = 2
    private val FRAGMENT_PROFILE = 3
    private val FRAGMENT_CHANGE_PASS = 4

    private var currentFragment = FRAGMENT_HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBar()
        initUI()
    }

    private fun setStatusBar() {
        val window: Window = this.window

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // finally change the color
        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryColor)
    }

    private fun initUI() {
        toolBar = findViewById(R.id.toolBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        header = findViewById(R.id.header)
        buttonMenu = findViewById(R.id.buttonMenu)
        textViewTitle = header?.findViewById(R.id.textViewTitle)

        setSupportActionBar(toolBar)

        textViewTitle?.text = getString(R.string.menu_home)
        toolBar?.title = "Home"

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolBar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout?.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView?.setNavigationItemSelectedListener(this)
        bottomNavigation?.setOnItemSelectedListener(this)
        buttonMenu?.setOnClickListener(this)
        replaceFragment(HomeFragment.newInstance())
        navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuHome) {
            if (currentFragment != FRAGMENT_HOME) {
                replaceFragment(HomeFragment.newInstance())
                currentFragment = FRAGMENT_HOME
                bottomNavigation?.selectedItemId = R.id.menuHome
                setCheckedDrawerMenu()
            }
        } else if (item.itemId == R.id.menuActivity) {
            if (currentFragment != FRAGMENT_ACTIVITY) {
                replaceFragment(ActivityFragment.newInstance())
                currentFragment = FRAGMENT_ACTIVITY
                bottomNavigation?.selectedItemId = R.id.menuActivity
                setCheckedDrawerMenu()
            }
        } else if (item.itemId == R.id.menuSetting) {
            if (currentFragment != FRAGMENT_SETTING) {
                replaceFragment(SettingFragment.newInstance())
                currentFragment = FRAGMENT_SETTING
                bottomNavigation?.selectedItemId = R.id.menuSetting
                setCheckedDrawerMenu()
            }
        } else if (item.itemId == R.id.menuProfile) {
            if (currentFragment != FRAGMENT_PROFILE) {
                replaceFragment(ProfileFragment.newInstance())
                currentFragment = FRAGMENT_PROFILE
                bottomNavigation?.selectedItemId = R.id.menuProfile
                setCheckedDrawerMenu()
            }
        } else if (item.itemId == R.id.menuChangePass) {
            if (currentFragment != FRAGMENT_CHANGE_PASS) {
                replaceFragment(ChangePassWordFragment.newInstance())
                currentFragment = FRAGMENT_CHANGE_PASS
                setCheckedDrawerMenu()
            }
        }
        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setCheckedDrawerMenu() {
        when (currentFragment) {
            FRAGMENT_HOME -> {
                navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = true
                navigationView?.menu?.findItem(R.id.menuActivity)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuSetting)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuProfile)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuChangePass)?.isChecked = false
                textViewTitle?.text = getString(R.string.menu_home)
                toolBar?.title = "Home"
            }
            FRAGMENT_ACTIVITY -> {
                navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuActivity)?.isChecked = true
                navigationView?.menu?.findItem(R.id.menuSetting)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuProfile)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuChangePass)?.isChecked = false
                textViewTitle?.text = getString(R.string.menu_activity)
                toolBar?.title = "Activity"
            }
            FRAGMENT_SETTING -> {
                navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuActivity)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuSetting)?.isChecked = true
                navigationView?.menu?.findItem(R.id.menuProfile)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuChangePass)?.isChecked = false
                textViewTitle?.text = getString(R.string.menu_setting)
                toolBar?.title = "Setting"
            }
            FRAGMENT_PROFILE -> {
                navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuActivity)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuSetting)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuProfile)?.isChecked = true
                navigationView?.menu?.findItem(R.id.menuChangePass)?.isChecked = false
                textViewTitle?.text = getString(R.string.menu_profile)
                toolBar?.title = "Profile"
            }
            FRAGMENT_CHANGE_PASS -> {
                navigationView?.menu?.findItem(R.id.menuHome)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuActivity)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuSetting)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuProfile)?.isChecked = false
                navigationView?.menu?.findItem(R.id.menuChangePass)?.isChecked = true
                toolBar?.title = "Change password"
            }
        }
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

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.buttonMenu -> {
                if (drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
                    drawerLayout?.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout?.openDrawer(GravityCompat.START)
                }
            }
        }
    }
}