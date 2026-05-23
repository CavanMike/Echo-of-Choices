package com.andriod.echoofchoices.screen.Register

class RegisterPresenter(
    private val view: RegisterContact.View,
    private val model: RegisterModel
) : RegisterContact.Presenter {

    override fun onRegisterClicked(username: String, password: String, confirmPassword: String) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showEmptyFieldsError()
            return
        }

        if (password != confirmPassword) {
            view.showPasswordMismatchError()
            return
        }

        model.registerUser(username, password)
        view.showRegisterSuccess()
        view.navigateToLogin()
    }
}
