package com.practicletaskapp.network

class WebFields {

    //Todo: Api Call Releted Comman and static Data
    companion object {

        const val BASE_URL = "http://180.211.99.165:8080/jaisal/Sweet_Science_Boxing/v8/"


        const val LOGIN_PATH_PARAM = "user/sign_in"//P

        //Common request param
        const val RESPONSE_MESSAGE = "message"
        const val RESPONSE_ERRORS = "errors"

        const val REQUEST_EMAIL = "email"
        const val REQUEST_MOBILE = "mobile"
        const val REQUEST_PASSWORD = "password"
        const val REQUEST_DEVICE_ID = "device_id"

        const val REQUEST_AUTH = "Authorization"
        const val REQUEST_ACCEPT= "Accept"
        const val REQUEST_APPL_JSON="application/json"
        const val REQUEST_BEARER = "Bearer "


    }


}