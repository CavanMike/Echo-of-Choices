package com.andriod.echoofchoices.screen.Register

class RegisterContact {
    interface View {
        fun showEmptyFieldsError()
        fun showPasswordMismatchError()
        fun showUsernameTakenError()
        fun showRegisterSuccess()
        fun navigateToLogin()
    }

    interface Presenter {
        fun onRegisterClicked(username: String, password: String, confirmPassword: String)
    }
}
