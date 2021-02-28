package com.practicletaskapp.network

class WebFields {

    //Todo: Api Call Releted Comman and static Data
    companion object {
        //        const val PAY_TM_CALL_BACK_URL = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID="  //For Development

        const val BASE_URL = "http://18.188.115.230/api/" //For Live
        const val IMAGE_BASE_URL = "http://18.188.115.230/storage/app/"


        const val LOGIN_PATH_PARAM = "login"//P

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