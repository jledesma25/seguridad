package com.jledesma.operacion

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecurityPreferences {

    fun sumar(numero1:Int, numero2:Int): Int{
        return numero1 + numero2
    }

    fun Context.encryptedPreferences(preferencesName:String) : SharedPreferences {

        val masterKeyAlias :String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            preferencesName,
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    }

    fun saveToken(token:String, encryptedPreferences: SharedPreferences){
        encryptedPreferences.edit().putString(Constantes.TOKEN,token).apply()
    }

    fun getToken(encryptedPreferences: SharedPreferences) : String {
        return encryptedPreferences.getString(Constantes.TOKEN,"") ?: ""
    }

}