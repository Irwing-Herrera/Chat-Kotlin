package com.iherrera.chatkotlin.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.others.*
import kotlinx.android.synthetic.main.activity_login.*

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

            if (isValidEmail(email) && isValidPassword(password)) {
                _logIn(email, password)
            } else {
                toast("Please make sure all the data is correct.")
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

        editTextEmail.validate {
            editTextEmail.error = if (isValidEmail(it)) null else "Email is not valid"
        }

        editTextPassword.validate {
            editTextPassword.error = if (isValidPassword(it)) null else "Password should contain 1 lowercase, 1 uppercase, 1 number, 1 special character and 4 characters length at least."
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
                    if (mAuth!!.currentUser!!.isEmailVerified) {
                        toast("User is now logged in.")
                    } else {
                        toast("User must confirm email first.")
                    }
                } else {
                    toast("An unexpected error occurred, please try again.")
                }
            }
    }

}