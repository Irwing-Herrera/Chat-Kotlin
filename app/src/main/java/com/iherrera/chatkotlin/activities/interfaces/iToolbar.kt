package com.iherrera.chatkotlin.activities.interfaces

import androidx.appcompat.widget.Toolbar

/**
 * Interfaz de implementacion de Toolbar
 */
interface iToolbar {
    /**
     * Cargar Toolbar
     *
     * @param {Toolbar?} toolbar
     */
    fun toolbarToLoad(toolbar: Toolbar?)
    /**
     * Habilitar Toolbar en Activity
     *
     * @param {Boolean} value
     */
    fun enableHomeDisplay(value: Boolean)
}