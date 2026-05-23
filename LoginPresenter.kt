package com.andriod.echoofchoices.screen.login

class LoginPresenter(
    private val view: LoginActivity,
    private val model: LoginModel
    ):LoginContact.Presenter{

    override fun isValidCredentials(username: String,password: String){

        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            view.showEmptyMessage()
        }else{

            if(model.isValidCredentials(username, password)) {
                view.showSucuessMessage()
                view.showDashboardScreen()
            }else{
                view.showInvalidCredentials()
            }
        }
    }


}