package com.iherrera.chatkotlin.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.utils.isValidEmail
import com.iherrera.chatkotlin.activities.utils.openActivity
import com.iherrera.chatkotlin.activities.utils.toast
import com.iherrera.chatkotlin.activities.utils.validate
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.editTextEmail

class ForgotPasswordActivity : AppCompatActivity() {

    /**
     * Declara una instancia de FirebaseAuth
     * Utliza carga peresosa, la cual se inicializa cuando se manda a llamar la variable
     */
    private val mAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        editTextEmail.validate {
            editTextEmail.error = if (isValidEmail(it)) null else "Email is not valid"
        }

        buttonGoLogIn.setOnClickListener {
            openActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        buttonForgot.setOnClickListener {
            val email = editTextEmail.text.toString()

            if (isValidEmail(email)) {
                mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener(this) {
                    toast("Email has been sent to reset your password.")
                    openActivity<LoginActivity> {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            } else {
                toast("Please make sure the email address is correct.")
            }
        }
    }
}