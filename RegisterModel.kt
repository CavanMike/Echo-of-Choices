package com.andriod.echoofchoices.screen.Register

import com.andriod.echoofchoices.app.CustomApp

class RegisterModel(private val app: CustomApp) {

    fun registerUser(username: String, password: String) {
        app.registerUser(username, password)
    }

    fun isAlreadyRegistered(): Boolean {
        return app.isRegistered()
    }
}
