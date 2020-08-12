package com.iherrera.chatkotlin.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.adapters.PagerAdapter
import com.iherrera.chatkotlin.activities.fragments.ChatFragment
import com.iherrera.chatkotlin.activities.fragments.InfoFragment
import com.iherrera.chatkotlin.activities.fragments.RatesFragment
import com.iherrera.chatkotlin.activities.others.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity() {

    /**
     * Fragment previamete seleccionado
     */
    private var prevBottomSelected: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbar as Toolbar)

        _setUpViewPager(_getPagerAdapter())
        _setUpBottomNavigationBar()
    }

    /**
     * Agrega todos los fragments al adapter
     *
     * @return {PagerAdapter}
     */
    private fun _getPagerAdapter() : PagerAdapter {
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragments(InfoFragment())
        adapter.addFragments(RatesFragment())
        adapter.addFragments(ChatFragment())
        return adapter
    }

    /**
     * Detectar cual fragment esta seleccionado
     *
     * @param {PagerAdapter} adapter
     */
    private fun _setUpViewPager(adapter: PagerAdapter) {
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                if (prevBottomSelected == null)
                    bottomNavigation.menu.getItem(0).isChecked = false
                else
                    prevBottomSelected!!.isChecked = false

                bottomNavigation.menu.getItem(position).isChecked = true
                prevBottomSelected = bottomNavigation.menu.getItem(position)
            }
        })
    }

    /**
     * Obtiene el item seleccionado de Bottom Navigation Bar
     */
    private fun _setUpBottomNavigationBar() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_info -> {
                    viewPager.currentItem = 0; true
                }
                R.id.bottom_nav_rates -> {
                    viewPager.currentItem = 1; true
                }
                R.id.bottom_nav_chat -> {
                    viewPager.currentItem = 2; true
                }
                else -> false
            }
        }
    }
}