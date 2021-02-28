package com.practicletaskapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practicletaskapp.R
import com.practicletaskapp.ui.login.LoginActivity
import com.practicletaskapp.utils.GlobalClass
import com.practicletaskapp.utils.SessionManager.SessionManager
import java.util.*

class SplashActivity : AppCompatActivity() {



    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        context = SplashActivity@ this
        splashTimeOut()
    }


    private fun splashTimeOut() {
        try {
            val timer = Timer()
            timer.schedule(object : TimerTask() {

                override fun run() {

                    if(SessionManager.getIsUserLoggedin(context)) {
                        val intent = Intent(context, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }else{
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }

                }
            }, GlobalClass.SPLASH_TIMEOUT)
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }
}
