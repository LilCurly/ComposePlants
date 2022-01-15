package com.example.soplant.commons

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SharedPreferencesManager constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val SHARED_PREFS_FILE: String = "soplant_sharedpreferences"
        private var shared: SharedPreferencesManager? = null

        fun shared(): SharedPreferencesManager {
            return shared!!
        }

        fun initializeSharedPreferencesManager(applicationContext: Context) {
            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
            val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
            val sharedPreferences = EncryptedSharedPreferences.create(
                SHARED_PREFS_FILE,
                mainKeyAlias,
                applicationContext,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

            shared = SharedPreferencesManager(sharedPreferences)
        }
    }

    fun getUserId(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_USER_ID, "") ?: ""
    }

    fun storeUserId(userId: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_ID, userId)
            apply()
        }
    }

    fun getUserUsername(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_USER_USERNAME, "") ?: ""
    }

    fun storeUserLocation(userLocation: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_LOCATION, userLocation)
            apply()
        }
    }

    fun getUserLocation(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_USER_LOCATION, "") ?: ""
    }

    fun storeUserVerified(userVerified: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(Constants.SharedPreferences.PREF_USER_VERIFIED, userVerified)
            apply()
        }
    }

    fun isUserVerified(): Boolean {
        return sharedPreferences.getBoolean(Constants.SharedPreferences.PREF_USER_VERIFIED, false)
    }

    fun storeUserUsername(userName: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_USERNAME, userName)
            apply()
        }
    }

    fun getUserEmail(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_USER_EMAIL, "") ?: ""
    }

    fun storeUserEmail(userEmail: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_EMAIL, userEmail)
            apply()
        }
    }

    fun getUserName(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_USER_NAME, "") ?: ""
    }

    fun storeUserName(userName: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_NAME, userName)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        val status = sharedPreferences.getString(Constants.SharedPreferences.PREF_LOGIN_STATUS, Constants.LoginStatus.STATUS_LOGGED_OUT)
        return status?.equals(Constants.LoginStatus.STATUS_LOGGED_IN) ?: false
    }

    fun storeLoggedIn() {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_LOGIN_STATUS, Constants.LoginStatus.STATUS_LOGGED_IN)
            apply()
        }
    }

    fun signOut() {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_NAME, "")
            putString(Constants.SharedPreferences.PREF_USER_ID, "")
            putString(Constants.SharedPreferences.PREF_USER_EMAIL, "")
            putString(Constants.SharedPreferences.PREF_USER_USERNAME, "")
            putString(Constants.SharedPreferences.PREF_LOGIN_STATUS, Constants.LoginStatus.STATUS_LOGGED_OUT)
            apply()
        }
    }
}