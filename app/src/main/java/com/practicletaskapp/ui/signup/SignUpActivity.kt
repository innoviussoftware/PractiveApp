package com.practicletaskapp.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.practicletaskapp.MainActivity
import com.practicletaskapp.R
import com.practicletaskapp.ui.login.LoginActivity
import com.practicletaskapp.utils.GlobalMethods
import com.practicletaskapp.utils.SessionManager.SessionManager
import com.practicletaskapp.utils.UtilsJava

class SignUpActivity : AppCompatActivity() {

    lateinit var edtTvFirstNameASUP: EditText
    lateinit var edtTvLastNameASUP: EditText
    lateinit var edtTvMobileASUP: EditText
    lateinit var edtTvEmailASUP: EditText
    lateinit var edtTvPassWrdASUP: EditText
    lateinit var edtTvAddressSUP: EditText
    lateinit var rdoGrpSexASUP: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        getIds()
    }

    private fun getIds() {
        edtTvFirstNameASUP = findViewById(R.id.edtTvFirstNameASUP)
        edtTvLastNameASUP = findViewById(R.id.edtTvLastNameASUP)
        edtTvPassWrdASUP = findViewById(R.id.edtTvPasswordSUP)
        edtTvMobileASUP = findViewById(R.id.edtTvPhoneSUP)
        edtTvEmailASUP = findViewById(R.id.edtTvEmailASUP)
        edtTvAddressSUP = findViewById(R.id.edtTvAddressSUP)
        edtTvFirstNameASUP = findViewById(R.id.edtTvFirstNameASUP)
        rdoGrpSexASUP = findViewById(R.id.rdoGrpSexASUP)
        rdoGrpSexASUP.setOnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
            val rb: RadioButton = findViewById<View>(checkedId) as RadioButton
            strGender = rb.text.toString()
        }
    }

    fun loginOpen(view: View) {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun signUpClick(view: View) {

        if (isValidation()) {
            UtilsJava.showToast(this, getString(R.string.signup_success_msg))
            SessionManager.setIsUserLoggedin(this, true)
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    lateinit var strFirstName: String
    lateinit var strLastName: String
    lateinit var strPhone: String
    lateinit var strAddress: String
    lateinit var strEmail: String
    lateinit var strPassword: String
    var strGender: String? = "Female"

    private fun isValidation(): Boolean {
        strFirstName = edtTvFirstNameASUP.text.toString().trim()
        strLastName = edtTvLastNameASUP.text.toString().trim()
        strPhone = edtTvMobileASUP.text.toString().trim()
        strEmail = edtTvEmailASUP.text.toString().trim()
        strPassword = edtTvPassWrdASUP.text.toString().trim()
        strAddress = edtTvAddressSUP.text.toString().trim()

        if (strFirstName.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.first_name_empty))
            edtTvFirstNameASUP.requestFocus()
            return false
        } else if (strLastName.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.last_name_empty))
            edtTvLastNameASUP.requestFocus()
            return false
        } else if (strPhone.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.phone_no_empty))
            edtTvMobileASUP.requestFocus()
            return false
        } else if (strPhone.length < 8) {
            UtilsJava.showToast(this, getString(R.string.phone_no_valid))
            edtTvMobileASUP.requestFocus()
            return false
        } else if (strEmail.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.email_empty))
            edtTvEmailASUP.requestFocus()
            return false
        } else if (!GlobalMethods.isEmailValid(strEmail)) {
            UtilsJava.showToast(this, getString(R.string.email_valid))
            edtTvEmailASUP.requestFocus()
            return false
        } else if (strPassword.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.password_empty))
            edtTvPassWrdASUP.requestFocus()
            return false
        } else if (strPassword.length < 6) {
            UtilsJava.showToast(this, getString(R.string.password_valid))
            edtTvPassWrdASUP.requestFocus()
            return false
        } else if (strGender.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.gender_empty))
            return false
        } else if (strAddress.equals("", ignoreCase = true)) {
            UtilsJava.showToast(this, getString(R.string.address_empty))
            edtTvAddressSUP.requestFocus()
            return false
        }

        return true
    }
}