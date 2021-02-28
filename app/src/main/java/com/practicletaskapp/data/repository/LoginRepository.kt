package com.practicletaskapp.data.repository

import com.practicletaskapp.data.network.SafeAPIRequest
import com.practicletaskapp.data.responce.LoginResponse
import com.practicletaskapp.network.MyApi
import com.google.gson.JsonObject

//Todo: MyApi connected Repository.
// SafeAPIRequest=  Using Api call Handle Data and Exceptions
class LoginSignUpRepository(private val api: MyApi): SafeAPIRequest() {


    suspend fun callLoginApi(email:String,password:String,device_id:String): LoginResponse {
        //Todo: Api call Execute using request param, and return values "LoginResponse" as per set return type/data
        return apiRequest { api.callExecuteLogin(email,password,device_id,"android") }
    }

}