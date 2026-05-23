package com.andriod.echoofchoices.screen.Profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.app.CustomApp
import com.andriod.echoofchoices.screen.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Get the username — prefer from CustomApp (single source of truth)
        val username = (application as CustomApp).getUser().username
            .ifEmpty { intent.getStringExtra("USERNAME") ?: "Unknown" }

        findViewById<TextView>(R.id.tv_profile_username).text = "Username: $username"

        findViewById<Button>(R.id.btn_back_dashboard).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
