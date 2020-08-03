package com.iherrera.chatkotlin.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.others.openActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        buttonGoLogIn.setOnClickListener {
            openActivity<LoginActivity>()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}