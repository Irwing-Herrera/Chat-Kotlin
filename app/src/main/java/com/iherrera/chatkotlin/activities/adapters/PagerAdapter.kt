package com.iherrera.chatkotlin.activities.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Adaptador de fragment de menu
 *
 * @param {FragmentManager} manager
 */
class PagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    /**
     * Arreglo de fragments
     *
     * @property {ArrayList<Fragment>} fragmentList
     */
    private val fragmentList = ArrayList<Fragment>()

    /**
     * Obtener fragmento de listado
     *
     * @param {Int} position
     * @return {Fragment}
     */
    override fun getItem(position: Int) = fragmentList[position]

    /**
     * Obtiene el tamaño del arreglo
     *
     * @return {Int}
     */
    override fun getCount() = fragmentList.size

    /**
     * Agregar un fragment al arreglo
     *
     * @param {fragment} Fragment
     * @return {Boolean}
     */
    fun addFragments(fragment: Fragment) = fragmentList.add(fragment)
}