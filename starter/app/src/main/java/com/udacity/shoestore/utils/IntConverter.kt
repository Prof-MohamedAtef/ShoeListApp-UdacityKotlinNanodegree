package com.udacity.shoestore.utils

import android.text.TextUtils
import androidx.databinding.InverseMethod

object IntConverter {
    @InverseMethod("convertDoubleToString")
    @JvmStatic
    fun convertStringToDouble(value: String):Double?{
        val pattern = Regex("/^[0-9]+(\\\\.[0-9]+)?\$")
        if (value.contains(".")){
            return value.toDoubleOrNull()
        }else if (value.matches(pattern)){
            return value.toDoubleOrNull()
        }else if(value.contains("")){
            return 0.0
        } else if (!TextUtils.isDigitsOnly(value)){
            return 0.0
        } else return 0.0
    }

    @JvmStatic
    fun convertDoubleToString(value: Double?): String {
          return ""
    }

}