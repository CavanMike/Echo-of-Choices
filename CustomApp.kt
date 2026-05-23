package com.andriod.echoofchoices.app

import android.app.Application
import android.util.Log
import com.andriod.echoofchoices.data.User

class CustomApp : Application() {

    private var user = User("", "")

    override fun onCreate() {
        super.onCreate()
        Log.e("Custom App", "OnCreate is called")
    }

    fun getUser() = this.user

    fun registerUser(username: String, password: String) {
        user = User(username, password)
    }

    fun isRegistered() = user.username.isNotEmpty()
}
