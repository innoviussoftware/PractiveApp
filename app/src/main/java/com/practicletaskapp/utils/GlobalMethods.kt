package com.practicletaskapp.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import java.util.regex.Pattern.compile

class GlobalMethods {

    //Todo: Create Global Method as per using all project
    companion object {
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager
            connectivityManager.activeNetworkInfo.also {
                return it != null && it.isConnected
            }
        }

        fun isEmailValid(email: String): Boolean {
            return Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-].+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            ).matcher(email).matches()

          /*  return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
            ).matcher(email).matches()*/
        }

        fun convertDate(sendDate:String): String{
            var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val newsDate = sendDate
            var formatedDate:String?=""
            if (newsDate != null) {
                val newDate = format.parse(newsDate)
                format = SimpleDateFormat("MMM dd, yyyy")
                 formatedDate = format.format(newDate)

            }

            return formatedDate!!
        }

        fun convertTime(sendTime:String): String{
            var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val newsDate = sendTime
            var formatedDate:String?=""
            if (newsDate != null) {
                val newDate = format.parse(newsDate)
                format = SimpleDateFormat("hh:mm aa")
                formatedDate = format.format(newDate)

            }

            return formatedDate!!
        }

        @Throws(ParseException::class)
        fun formatToYesterdayOrToday(date: String): String {
            val dateTime = SimpleDateFormat("EEE hh:mma MMM d, yyyy").parse(date)
            val calendar = Calendar.getInstance()
            calendar.setTime(dateTime)
            val today = Calendar.getInstance()
            val yesterday = Calendar.getInstance()
            yesterday.add(Calendar.DATE, -1)
            val timeFormatter = SimpleDateFormat("hh:mma")

            return if (calendar.get(Calendar.YEAR) === today.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) === today.get(Calendar.DAY_OF_YEAR)) {
                "Today " + timeFormatter.format(dateTime)
            } else if (calendar.get(Calendar.YEAR) === yesterday.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) === yesterday.get(Calendar.DAY_OF_YEAR)) {
                "Yesterday " + timeFormatter.format(dateTime)
            } else {
                date
            }
        }

    }
}