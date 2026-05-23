package com.andriod.echoofchoices.screen.login

import com.andriod.echoofchoices.app.CustomApp

class LoginModel(private val app: CustomApp) {
    fun isValidCredentials(username: String,password: String):Boolean{
        val user = app.getUser()
        return user.username.equals(username,false)
                &&user.password.equals(password,false)
    }
}