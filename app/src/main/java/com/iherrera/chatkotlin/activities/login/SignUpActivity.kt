package com.iherrera.chatkotlin.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.others.openActivity
import com.iherrera.chatkotlin.activities.others.toast
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    /**
     * Declara una instancia de FirebaseAuth
     * Utliza un carga peresosa, la cual se inicializa cuando se manda a llamar la variable
     */
    private val mAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        buttonGoLogIn.setOnClickListener {
            openActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextConfirmPassword.text.toString()

            if (_isValidEmailAndPassword(email, password)) {
                _signUpByEmail(email, password)
            } else {
                toast("Please fill all the data and confirm password is correct.")
            }
        }
    }

    /**
     * Realiza el logueo de una cuenta a Firebase
     *
     * @param {String} email
     * @param {String} pasword
     */
    private fun _signUpByEmail(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("An email has been sent to you. Please, confirm before sign in.")
                    val user = mAuth!!.currentUser
                } else {
                    toast("An unexpected error occurred, please try again.")
                }
            }
    }

    /**
     * Comprueba si no estan vacios los text y si son iguales los passwords
     */
    private fun _isValidEmailAndPassword(emai: String, password: String): Boolean {
        return !emai.isNullOrEmpty() &&
                !password.isNullOrEmpty() &&
                password === editTextConfirmPassword.text.toString()

    }
}