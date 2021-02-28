package com.fia.data.repository

import com.example.mvvm_demo.data.network.SafeAPIRequest
import com.fia.data.responce.LoginResponse
import com.fia.network.MyApi
import com.google.gson.JsonObject

//Todo: MyApi connected Repository.
// SafeAPIRequest=  Using Api call Handle Data and Exceptions
class LoginRepository(private val api: MyApi): SafeAPIRequest() {


    suspend fun callLogin(email:String,password:String,device_id:String): LoginResponse {
        //Todo: Api call Execute using request param, and return values "LoginResponse" as per set return type/data
        return apiRequest { api.callExecuteLogin(email,password,device_id) }
    }

}