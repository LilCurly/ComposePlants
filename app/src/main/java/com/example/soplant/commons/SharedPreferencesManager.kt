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

    fun getAccountId(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_ACCOUNT_ID, "") ?: ""
    }

    fun storeAccountId(accountId: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_ACCOUNT_ID, accountId)
            apply()
        }
    }

    fun getAccountUsers(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_ACCOUNT_USERS, "") ?: ""
    }

    fun storeAccountUsers(accountUsers: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_ACCOUNT_USERS, accountUsers)
            apply()
        }
    }

    fun getLastUserId(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_LAST_USER_ID, "") ?: ""
    }

    fun storeLastUserId(lastUserId: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_LAST_USER_ID, lastUserId)
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

    fun getAccountEmail(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_ACCOUNT_EMAIL, "") ?: ""
    }

    fun storeAccountEmail(userEmail: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_ACCOUNT_EMAIL, userEmail)
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

    fun getSocialMethod(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_SOCIAL_METHOD, "") ?: ""
    }

    fun storeSocialMethod(socialMethod: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_SOCIAL_METHOD, socialMethod)
            apply()
        }
    }

    fun getPictureUrl(): String {
        return sharedPreferences.getString(Constants.SharedPreferences.PREF_PROFILE_URL, "") ?: ""
    }

    fun storePictureUrl(profileUrl: String) {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_PROFILE_URL, profileUrl)
            apply()
        }
    }

    fun signOut() {
        with(sharedPreferences.edit()) {
            putString(Constants.SharedPreferences.PREF_USER_NAME, "")
            putString(Constants.SharedPreferences.PREF_ACCOUNT_ID, "")
            putString(Constants.SharedPreferences.PREF_ACCOUNT_EMAIL, "")
            putString(Constants.SharedPreferences.PREF_USER_USERNAME, "")
            putString(Constants.SharedPreferences.PREF_USER_LOCATION, "")
            putBoolean(Constants.SharedPreferences.PREF_USER_VERIFIED, false)
            putString(Constants.SharedPreferences.PREF_LOGIN_STATUS, Constants.LoginStatus.STATUS_LOGGED_OUT)
            apply()
        }
    }
}