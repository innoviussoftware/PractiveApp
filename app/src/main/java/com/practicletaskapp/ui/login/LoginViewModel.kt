package com.practicletaskapp.ui.login



import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicletaskapp.R
import com.practicletaskapp.data.repository.LoginSignUpRepository
import com.practicletaskapp.data.responce.LoginResponse
import com.practicletaskapp.utils.*

class LoginViewModel (private val userRepository: LoginSignUpRepository) : ViewModel() {

    var authData: MutableLiveData<LoginResponse>? = null

    val userDAta by lazyDeferred {
        //   userRepository.getuserData()
    }

    var apiResponse: LoginResponse? = null

    fun callLoginExecuteApi(
        context: Context,
        email: String,
        password: String,device_id:String
    ): LiveData<LoginResponse> {
        authData = MutableLiveData<LoginResponse>()

        Coroutines.main {
            try {
                apiResponse = userRepository.callLoginApi(email, password,device_id)
                authData!!.value = apiResponse
            } catch (e: ApiExceptions) {
                //authData!!.value = e.message
                authData!!.value = null

                UtilsJava.showToast(context, e.message.toString())
            } catch (e: NoInternetException) {

            }
        }
        return authData!!
    }
}