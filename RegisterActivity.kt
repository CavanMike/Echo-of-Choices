package com.andriod.echoofchoices.screen.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.app.CustomApp
import com.andriod.echoofchoices.screen.login.LoginActivity
import com.andriod.echoofchoices.screen.utils.toast

class RegisterActivity : AppCompatActivity(), RegisterContact.View {

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter = RegisterPresenter(
            this,
            RegisterModel(application as CustomApp)
        )

        findViewById<Button>(R.id.btn_register).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString().trim()
            val password = findViewById<EditText>(R.id.et_reg_password).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.et_reg_confirm_password).text.toString()

            presenter.onRegisterClicked(username, password, confirmPassword)
        }
    }

    override fun showEmptyFieldsError() {
        toast("All fields must be filled in")
    }

    override fun showPasswordMismatchError() {
        toast("Passwords do not match")
    }

    override fun showUsernameTakenError() {
        toast("Username is already taken")
    }

    override fun showRegisterSuccess() {
        toast("Account created! Welcome, back .")
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
