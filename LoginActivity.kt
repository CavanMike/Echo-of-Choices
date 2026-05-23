package com.andriod.echoofchoices.screen.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.screen.utils.getEditTextValue
import com.andriod.echoofchoices.screen.utils.toast
import com.andriod.echoofchoices.app.CustomApp
import com.andriod.echoofchoices.screen.Register.RegisterActivity
import com.andriod.echoofchoices.screen.dashboard.DashboardActivity

class LoginActivity : AppCompatActivity(), LoginContact.View {
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, LoginModel(application as CustomApp))

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val username = getEditTextValue(R.id.etEmail)
            val password = getEditTextValue(R.id.etPassword)
            presenter.isValidCredentials(username, password)
        }

        findViewById<Button>(R.id.btnGoToRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun showEmptyMessage() {
        toast("Field cannot be empty")
    }

    override fun showSucuessMessage() {
        toast("Welcome back!")
    }

    override fun showDashboardScreen() {
        val username = getEditTextValue(R.id.etEmail)
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("USERNAME", username)
        startActivity(intent)
        finish()
    }

    override fun showInvalidCredentials() {
        toast("Invalid username or password")
    }
}
