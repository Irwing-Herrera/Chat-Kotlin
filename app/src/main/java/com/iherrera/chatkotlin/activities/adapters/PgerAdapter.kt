package com.iherrera.chatkotlin.activities.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    /**
     * Arreglo de fragments
     */
    private val fragmentList = ArrayList<Fragment>()

    /**
     * Obtener fragmento de listado
     *
     * @param {Int} position
     * @return {Fragment}
     */
    override fun getItem(position: Int): Fragment = fragmentList[position]

    /**
     * Obtiene el tamaño del arreglo de el arreglo de los fragments
     *
     * @return {Int}
     */
    override fun getCount(): Int = fragmentList.size

    /**
     * Agregar un fragment al arreglo
     *
     * @param {fragment} Fragment
     * @return {Boolean}
     */
    fun addFragments(fragment: Fragment): Boolean = fragmentList.add(fragment)
}