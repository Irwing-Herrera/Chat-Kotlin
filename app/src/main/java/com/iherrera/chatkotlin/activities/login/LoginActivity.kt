package com.iherrera.chatkotlin.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.MainActivity
import com.iherrera.chatkotlin.activities.utils.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    /**
     * Declara una instancia de FirebaseAuth
     * Utliza carga peresosa, la cual se inicializa cuando se manda a llamar la variable
     */
    private val mAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }

    /**
     * Declara una instacion de Google
     * Cliente de inicio de sesión de google
     */
    private val mGoogleSignInClient: GoogleSignInClient by lazy { _getGoogleSignIn() }

    /**
     * Request Code de inicio de sesion de google
     */
    private val RC_GOOGLE_SIGN_IN: Int = 99

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
            editTextPassword.error =
                if (isValidPassword(it)) null else "Password should contain 1 lowercase, 1 uppercase, 1 number, 1 special character and 4 characters length at least."
        }
        buttonLogInGoogle.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
        }
    }

    /**
     * Configurar el inicio de sesión de Google
     *
     * @return {GoogleSignInClient}
     */
    private fun _getGoogleSignIn(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, gso)
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

    /**
     * Verificar estado de inicio de sesión con cuenta de Google en Firebase
     *
     * @param {GoogleSignInAccount} account
     */
    private fun _firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    mGoogleSignInClient.signOut()
                    openActivity<MainActivity> {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                } else {
                    toast("Firebase Authentication failed:" + task.getException())
                }
            }
    }

    /**
     * Espera estado de codigo de respuesta de intent lanzado
     *
     * RC_GOOGLE_SIGN_IN -> 99
     * @param {Int} requestCode
     * @param {Int} resultCode
     * @param {Intent?} data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                _firebaseAuthWithGoogle(account);
            } catch (e: ApiException) {
                toast("Google login failed.")
            }
        }
    }

}