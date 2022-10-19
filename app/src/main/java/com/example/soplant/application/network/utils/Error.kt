package com.example.soplant.application.network.utils

import com.example.soplant.application.network.models.ErrorDto
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.ResponseBody

object Error {
    fun getErrorModel(errorBody: ResponseBody?): ErrorDto? {
        errorBody?.let {
            val element: JsonObject = JsonParser.parseReader(it.charStream()).asJsonObject
            return ErrorDto(
                errorType = element["errorType"].asString,
                errorMessage = element["errorMessage"].asString
            )
        }

        return null
    }
}