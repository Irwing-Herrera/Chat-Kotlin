package com.iherrera.chatkotlin.activities.interfaces

import androidx.appcompat.widget.Toolbar

interface iToolbar {
    fun toolbarToLoad(toolbar: Toolbar?)
    fun enableHomeDisplay(value: Boolean)
}