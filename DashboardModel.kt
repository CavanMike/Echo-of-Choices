package com.andriod.echoofchoices.screen.dashboard

import com.andriod.echoofchoices.app.CustomApp
import com.andriod.echoofchoices.data.User

class DashboardModel(private val app: CustomApp) {

    fun getLoggedInUser(): User {
        return app.getUser()
    }

    fun getWelcomeMessage(): String {
        val user = app.getUser()
        return "Welcome back, ${user.username}!"
    }

    fun logout() {
        // Future: clear session tokens, shared prefs, etc.
        // app.clearSession()
    }
}
