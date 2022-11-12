package com.developer.cubemarket.config.share_references

import android.content.Context
import android.content.SharedPreferences
import com.developer.cubemarket.`object`.user.Auth


class DataShareReferences {
    companion object{

        fun putEmailAndPass(context: Context, email: String, pass: String){
            val sharedPref: SharedPreferences =
                context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("email", email)
            editor.putString("pass", pass)
            editor.apply()
        }
        fun getEmailAndPass(context: Context): Auth{
            val sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val email = sharedPref.getString("email", "")
            val pass = sharedPref.getString("pass", "")
            return Auth(email.toString(), pass.toString())
        }
    }
}