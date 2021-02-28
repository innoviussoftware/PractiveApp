package com.practicletaskapp.utils

import java.text.DecimalFormat
import java.util.*

class GlobalClass {

    //Todo: Any Global Data and Values
    companion object {
        val SPLASH_TIMEOUT:Long=3000

        //Api call Code
        val Success_Code = 200
        val Internal_Server_Code = 500
        val Not_Found_Code = 401
        val Not_Data_Found_Code = 422

        var fia_weburl="www.fia-gujarat.org"
        var date_formate="YYYY-MM-DD" //2020-03-26



        //Complaint STATUS NAME
        val COMPLAINT_NEW_0 ="New"
        val COMPLAINT_IN_PROGRESS_1="In Progress"
        val COMPLAINT_ON_HOL_2 ="On Hold"
        val COMPLAINT_COMPLETED_3 ="Completed"
        val COMPLAINT_CLOSED_4 ="Closed"

    }
}