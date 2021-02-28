package com.practicletaskapp.data.responce

data class LoginResponse(
    var status_code:Int?=null,
    var message:String?=null,
    val `data`: Data
)

data class Data(
    val device_id: String,
    val email: String,
    val mobile: String,
    val name: String,
    val image:String,
    val role_id: Int,
    val token: String,
    val verified: Int
)