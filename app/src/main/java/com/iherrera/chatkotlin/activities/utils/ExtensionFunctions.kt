package com.iherrera.chatkotlin.activities.utils

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

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

/**
 * Validacion de un EditText en tiempo de escritura
 */
fun EditText.validate(validation: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            validation(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

/**
 * Verifica si un texto cumple con la estructura de un Email
 *
 * @param {String} email
 */
fun Activity.isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

/**
 * Verifica si un texto cumple con la estructura de un Password
 * Se crea una expesion Regex par validar
 *
 * @param {String} password
 */
fun Activity.isValidPassword(password: String): Boolean {
    // Necesita Contener -->    1 Num / 1 Minuscula / 1 Mayuscula / 1 Special / Min Caracteres 4
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}

/**
 * Verifica si un texto es igual a otro
 *
 * @param {String} password
 * @param {String} confirmPassword
 */
fun Activity.isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}