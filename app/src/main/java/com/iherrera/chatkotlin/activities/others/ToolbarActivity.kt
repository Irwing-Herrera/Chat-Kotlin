package com.iherrera.chatkotlin.activities.others

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.iherrera.chatkotlin.activities.interfaces.iToolbar

open class ToolbarActivity : AppCompatActivity(), iToolbar {

    private var _toolbar: Toolbar? = null

    override fun toolbarToLoad(toolbar: Toolbar?) {
        _toolbar = toolbar
        _toolbar?.let { setSupportActionBar(_toolbar) }
    }

    override fun enableHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }
}