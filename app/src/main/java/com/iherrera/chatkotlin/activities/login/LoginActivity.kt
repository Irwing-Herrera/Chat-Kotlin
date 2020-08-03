package com.iherrera.chatkotlin.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.others.openActivity
import com.iherrera.chatkotlin.activities.others.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {

    /**
     * Declara una instancia de FirebaseAuth
     * Utliza carga peresosa, la cual se inicializa cuando se manda a llamar la variable
     */
    private val mAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (_isValidEmailAndPassword(email, password)) {
                _logIn(email, password)
            } else {

            }
        }

        buttonCreateAccount.setOnClickListener {
            openActivity<SignUpActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
        textViewForgotPassword.setOnClickListener {
            openActivity<ForgotPasswordActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }

    /**
     * Realiza el logueo de una cuenta a Firebase
     *
     * @param {String} email
     * @param {String} pasword
     */
    private fun _logIn(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("User is now logged in.")
                } else {
                    toast("An unexpected error occurred, please try again.")
                }
            }
    }

    /**
     * Comprueba si no estan vacios los text
     *
     * @param {String} email
     * @param {String} password
     * @return {Boolean}
     */
    private fun _isValidEmailAndPassword(emai: String, password: String): Boolean {
        return !emai.isNullOrEmpty() && !password.isNullOrEmpty()
    }
}