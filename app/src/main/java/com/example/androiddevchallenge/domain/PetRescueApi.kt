package com.example.androiddevchallenge.domain

import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.net.ssl.*

/*
    Query Parameters
    https://www.petfinder.com/developers/v2/docs/#get-animal-types
 */
interface PetApi {

    @GET("animals")
    suspend fun getAnimals(
        @Query("type") type: String = "",
        @Query("breed") breed: String = "",
        @Query("page") page: String = "1"
    ): Response<Welcome>

}

object PetApiAdapter {
    var token: String? = null
    private const val HOST = "https://api.petfinder.com/v2/"
    val apiClient: PetApi = Retrofit.Builder()
        .baseUrl(HOST)
        .client(getOkHttp())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PetApi::class.java)

    private fun getOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor {
            val original = it.request()
            val new = original.newBuilder()
            if (token == null) {
                val tokenResponse = getToken().execute()
                if (tokenResponse.isSuccessful && tokenResponse.body != null) {
                    val gson = GsonBuilder().create()
                    token = gson.fromJson(
                        tokenResponse.body?.string(),
                        PetTokenResponse::class.java
                    ).access_token
                }
            }
            new.header("Authorization", "Bearer $token")
            return@addInterceptor it.proceed(new.build())
        }
        builder.addInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        )
        return builder.build()
    }

    private fun getToken(): Call {
        val client = OkHttpClient.Builder()
        client.addInterceptor {
            val original = it.request()
            val new = original.newBuilder()
            new.header("Accept", "application/problem+json")
            new.header("Content-Type", "application/x-www-form-urlencoded")
            return@addInterceptor it.proceed(new.build())
        }
        val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body = (
                "client_id=kuSafGNLv4DjH5USshaKW8obghJpDFaXBHgR5ZUddVv4Q8DS1l&" +
                        "client_secret=P89kBSqFpZM8IL3DCAOtHvGdVFRSmby6nrwsTW99&" +
                        "grant_type=client_credentials"
                )
            .toRequestBody(mediaType)
        val request: Request = Request.Builder()
            .url("https://api.petfinder.com/v2/oauth2/token")
            .method("POST", body)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()

        return client.build().newCall(request)
    }
}