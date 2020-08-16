package com.iherrera.chatkotlin.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.iherrera.chatkotlin.activities.MainActivity
import com.iherrera.chatkotlin.activities.utilities.openActivity

/**
 * Inicio de sesión y flujo de actividad principal
 */
class MainEmptyActivity : AppCompatActivity() {

    /**
     * Declara una instancia de FirebaseAuth
     */
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (mAuth.currentUser == null) {
            openActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        } else {
            openActivity<MainActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        // No volver a ser llamado
        finish()
    }
}