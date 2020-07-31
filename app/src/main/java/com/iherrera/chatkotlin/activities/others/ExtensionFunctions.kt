package com.iherrera.chatkotlin.activities.others

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

/**
 * Checa si un numero es Natural
 *  -> 0 : No es natural
 *  -> 1 : Es natural
 */
fun Int.isNatural() = this >= 0

/**
 * Muestra un Toast
 *
 * @param {CharSequence} message
 * @param {Int<Toast.LENGTH_SHORT | Toast.LENGTH_LONG>} duration
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

/**
 * Muestra un Toast
 *
 * @param {Int} resorceId
 * @param {Int: <Toast.LENGTH_SHORT | Toast.LENGTH_LONG>} duration
 */
fun Activity.toast(resorceId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, resorceId, duration).show()

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)

/**
 * Redirecciona a un Activity
 *
 * @param {<T>: Activity} activity
 * @param {Int<Toast.LENGTH_SHORT | Toast.LENGTH_LONG>} duration
 */
inline fun <reified T : Activity> Activity.openActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

/**
 * Obtiene la respuesta del permiso solicitado
 *
 * @param {String} action
 * @param {Int} requestCode
 */
fun Activity.goToActivityResult(action: String, requestCode: Int, init: Intent.() -> Unit = {}) {
    val intent = Intent(action)
    intent.init()
    startActivityForResult(intent, requestCode)
}