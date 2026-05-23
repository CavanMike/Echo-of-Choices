package com.andriod.echoofchoices.screen.dashboard

class DashboardContact {
    interface View {
        fun showWelcomeMessage(username: String)
        fun showLogoutConfirmation()
        fun navigateToProfile()
        fun navigateToLogin()
        fun showUserInfo(username: String)
    }

    interface Presenter {
        fun loadUserData()
        fun onProfileClicked()
        fun onLogoutClicked()
        fun confirmLogout()
    }
}
