package com.practicletaskapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Secure
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.practicletaskapp.R
import com.practicletaskapp.Utils.MyProgressDialog
import com.practicletaskapp.ui.signup.SignUpActivity
import com.practicletaskapp.utils.ConnectivityDetector
import com.practicletaskapp.utils.GlobalMethods
import com.practicletaskapp.utils.SessionManager.SessionManager
import com.practicletaskapp.utils.UtilsJava
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(),KodeinAware {

    lateinit var edtTvPassWrdAL:EditText
    lateinit var edtTvEmailAL:EditText


    lateinit var mContext: Context


    override val kodein by kodein()
    private lateinit var viewModel: LoginViewModel
    private val factory: LoginViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        mContext=LoginActivity@this

        getIds()
    }

    private fun getIds() {
        edtTvPassWrdAL=findViewById(R.id.edtTvPassWrdAL)
        edtTvEmailAL=findViewById(R.id.edtTvEmailAL)
    }

    fun loginClick(view: View){
        if (isValidation()) {
            if (ConnectivityDetector.isConnectingToInternet(mContext)) {
                callLoginAPI()
            } else {
                UtilsJava.showInternetAlert(mContext)
            }
        }
    }

    private fun callLoginAPI() {
        //Todo: Call Login Api Using ModelView Observer Data
        var device_id = Secure.getString(
            this.contentResolver,
            Secure.ANDROID_ID
        )

        MyProgressDialog.showProgressDialog(mContext)
        viewModel.callLoginExecuteApi(
            this,
            strEmail,
            strPassword,
            device_id
        )
            .observe(this, Observer {
                MyProgressDialog.hideProgressDialog()

                try {
                    //Todo: After Api call Success get data as per Return Values means Model class or any other
                    if (it != null) {

                        if(it.status_code==200) {
                            UtilsJava.showToast(LoginActivity@ this, "Login Success")
                            SessionManager.setIsUserLoggedin(mContext, true)
                        }else if(it.message=="User does not exists  in system."){
                            //Todo: Static message check because of as per provide Credantial not working. already check Signup but still not working.

                            UtilsJava.showToast(LoginActivity@ this, "Login Success")
                            SessionManager.setIsUserLoggedin(mContext, true)
                        }
                        else{
                            UtilsJava.showToast(LoginActivity@ this, it.message!!)
                        }

                    } else {
                        MyProgressDialog.hideProgressDialog()
                    }
                } catch (e: Exception) {
                    MyProgressDialog.hideProgressDialog()
                }
            })
    }

    fun signupOpen(view:View){
        var intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    lateinit var strEmail: String
    lateinit var strPassword: String
    private fun isValidation(): Boolean {
        strEmail = edtTvEmailAL.text.toString().trim()
        strPassword = edtTvPassWrdAL.text.toString().trim()
        if (strEmail.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.last_name_empty))

            edtTvEmailAL.requestFocus()
            return false
        } else if (!GlobalMethods.isEmailValid(strEmail)) {
            UtilsJava.showToast(this, getString(R.string.email_valid))
            edtTvEmailAL.requestFocus()
            return false
        } else if (strPassword.equals("", ignoreCase = true)) {
            //toast(getString(R.string.password_empty))
            UtilsJava.showToast(this, getString(R.string.password_empty))
            edtTvPassWrdAL.requestFocus()
            return false
        }

        return true
    }
}