package com.andriod.echoofchoices.screen.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.app.CustomApp
import com.andriod.echoofchoices.screen.Profile.ProfileActivity
import com.andriod.echoofchoices.screen.login.LoginActivity
import com.andriod.echoofchoices.screen.utils.toast
import com.andriod.echoofchoices.screen.charater.CharaterListViewActivity
import com.andriod.echoofchoices.screen.game.GameActivity

class DashboardActivity : AppCompatActivity(), DashboardContact.View {

    lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        presenter = DashboardPresenter(
            this,
            DashboardModel(application as CustomApp)
        )

        presenter.loadUserData()

        // ── Start Game button ─────────────────────────────────────────────────
        findViewById<Button>(R.id.btn_start_game).setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        findViewById<Button>(R.id.btn_to_profile).setOnClickListener {
            presenter.onProfileClicked()
        }

        findViewById<Button>(R.id.btn_to_charater).setOnClickListener {
            startActivity(Intent(this, CharaterListViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            presenter.onLogoutClicked()
        }
    }

    override fun showWelcomeMessage(username: String) {
        toast("Welcome back, $username!")
        findViewById<TextView>(R.id.tv_username).text = "Welcome Home, $username"
    }

    override fun showUserInfo(username: String) {
        findViewById<TextView>(R.id.tv_username).text = "Welcome Home, $username"
    }

    override fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Leave the Town?")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes, Leave") { _, _ ->
                presenter.confirmLogout()
            }
            .setNegativeButton("Stay", null)
            .show()
    }

    override fun navigateToProfile() {
        val username = (application as CustomApp).getUser().username
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("USERNAME", username)
        startActivity(intent)
    }

    override fun navigateToLogin() {
        toast("You have logged out")
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
