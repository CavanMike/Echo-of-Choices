package com.andriod.echoofchoices.screen.login

class LoginContact {
    interface View{
        fun showEmptyMessage()
        fun showSucuessMessage()
        fun showDashboardScreen()
        fun showInvalidCredentials()
    }

    interface Presenter{
        fun isValidCredentials(username: String ,password : String )
    }
}