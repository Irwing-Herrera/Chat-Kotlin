package com.iherrera.chatkotlin.activities.others

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.iherrera.chatkotlin.activities.interfaces.iToolbar

/**
 * Agregar Toolbar a una Activity al Heredar de ToolbarActivity
 */
open class ToolbarActivity : AppCompatActivity(), iToolbar {

    private var _toolbar: Toolbar? = null

    /**
     * Inicializar Toolbar
     *
     * @param {Toolbar?} toolbar
     */
    override fun toolbarToLoad(toolbar: Toolbar?) {
        _toolbar = toolbar
        _toolbar?.let { setSupportActionBar(_toolbar) }
    }
    /**
     * Habilitar visibilidad de Toolbar
     *
     * @param {Boolean} value
     */
    override fun enableHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }
}