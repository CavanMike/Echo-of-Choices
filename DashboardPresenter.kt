package com.andriod.echoofchoices.screen.dashboard

class DashboardPresenter(
    private val view: DashboardContact.View,
    private val model: DashboardModel
) : DashboardContact.Presenter {

    override fun loadUserData() {
        val user = model.getLoggedInUser()
        view.showWelcomeMessage(user.username)
        view.showUserInfo(user.username)
    }

    override fun onProfileClicked() {
        view.navigateToProfile()
    }

    override fun onLogoutClicked() {
        view.showLogoutConfirmation()
    }

    override fun confirmLogout() {
        model.logout()
        view.navigateToLogin()
    }
}

