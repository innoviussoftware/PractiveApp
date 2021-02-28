package com.practicletaskapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicletaskapp.data.repository.LoginSignUpRepository

class LoginViewModelFactory(private val loginRepository : LoginSignUpRepository)
    : ViewModelProvider.NewInstanceFactory() {

    //Todo: Using InstanceFactory as per using ViewModel Crete
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("LoginViewModelFactory exception")
    }

}