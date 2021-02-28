package com.practicletaskapp.ui.signup

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.practicletaskapp.ui.MainActivity
import com.practicletaskapp.R
import com.practicletaskapp.ui.login.LoginActivity
import com.practicletaskapp.utils.GlobalMethods
import com.practicletaskapp.utils.SessionManager.SessionManager
import com.practicletaskapp.utils.UtilsJava

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log

class SignUpActivity : AppCompatActivity() {

    lateinit var edtTvFirstNameASUP: EditText
    lateinit var edtTvLastNameASUP: EditText
    lateinit var edtTvMobileASUP: EditText
    lateinit var edtTvEmailASUP: EditText
    lateinit var edtTvPassWrdASUP: EditText
    lateinit var edtTvAddressSUP: EditText
    lateinit var rdoGrpSexASUP: RadioGroup

    lateinit var ivProfilePicASUP:ImageView

    lateinit var mContext:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mContext=SignUpActivity@this
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
        ivProfilePicASUP=findViewById(R.id.ivProfilePicASUP)


        rdoGrpSexASUP.setOnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
            val rb: RadioButton = findViewById<View>(checkedId) as RadioButton
            strGender = rb.text.toString()
        }

        ivProfilePicASUP.setImageDrawable(resources.getDrawable(R.drawable.user))
    }

    fun changeProfilePIc(view: View){
        try {
            if (checkPermission()) {
                CropImage.startPickImageActivity(this);
            }
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    private val MY_PERMISSIONS_REQUEST_CAMERA = 1

    private fun checkPermission(): Boolean {
        try {

            val AccessCamera =
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
            val ReadPermission =
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            val WritePermission =
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            val listPermissionsNeeded = ArrayList<String>()

            if (AccessCamera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA)
            }
            if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (WritePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(
                    this@SignUpActivity,
                    listPermissionsNeeded.toTypedArray(), MY_PERMISSIONS_REQUEST_CAMERA
                )
                return false
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    //Image set start
    private var mCropImageUri: Uri? = null
    var alPhotoFilePath: ArrayList<String>? = null
    var alPhotoUri: ArrayList<Uri>? = null
    var filePath: String? = ""


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    var imageUri: Uri? = null
                    // handle result of pick image chooser
                    if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

                        imageUri = CropImage.getPickImageResultUri(this, data)

                        if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                            mCropImageUri = imageUri
                            requestPermissions(
                                arrayOf(
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA
                                ), CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE
                            )
                        } else {
                            startCropImageActivity(imageUri)
                        }
                    } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                        try {
                            //   mCropImageUri = CropImage.getActivityResult(data).getUri();
                            val uri = CropImage.getActivityResult(data).getUri()

                              ivProfilePicASUP.setImageURI(uri)
                            
                        } catch (e: Exception) {

                            e.printStackTrace();

                        }

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
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

    //Set image .. start
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        if (ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            if (ContextCompat.checkSelfPermission(
                                    this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {

                            }
                        }
                    }

                } else {
                    //    checkPermission()
                }
            }
        }
    }

    private fun startCropImageActivity(imageUri: Uri) {
        CropImage.activity(imageUri)
            .start(this)
    }

}