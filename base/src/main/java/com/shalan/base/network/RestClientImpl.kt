package com.shalan.base.network

import android.content.Context
import android.util.Log.VERBOSE
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.shalan.base.services.SessionService
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Mohamed Shalan on 4/30/21
 */

abstract class RestClientImpl(
    protected val context: Context,
    protected val sessionService: SessionService,
    protected val adapterFactory: CallAdapter.Factory,
    protected val converterFactory: Converter.Factory,
    protected val tokenType: String
) : IRestClient<Retrofit> {

    private val AUTHORIZATION_KEY = "Authorization"

    private val REQUEST_DURATION = 1L

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var retrofitClient: Retrofit

    override fun getOkHttpClient(): OkHttpClient =
        if (this::okHttpClient.isInitialized) okHttpClient else buildOkHttpClient()

    private fun buildOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        //ADDING PROVIDED INTERCEPTORS
        getAdditionalInterceptors()?.forEach { interceptor ->
            this.addInterceptor(interceptor)
        }

        //ADDING HEADERS
        getAdditionalHeaders()?.let { headers ->
            this.addInterceptor { chain ->
                val newRequestBuilder = chain.request().newBuilder()

                for ((key, value) in headers) {
                    newRequestBuilder.addHeader(key, value)
                }

                //ADDING TOKEN IF EXIST
                if (sessionService.getSessionToken() != null)
                    newRequestBuilder.addHeader(
                        AUTHORIZATION_KEY,
                        "${tokenType}: ${sessionService.getSessionToken()}"
                    )

                chain.proceed(newRequestBuilder.build())
            }
        }

        this.addInterceptor(ChuckerInterceptor.Builder(context).build())
        this.addInterceptor(
            LoggingInterceptor.Builder().setLevel(Level.BODY).log(VERBOSE).build()
        )
        this.writeTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
        this.connectTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
        this.readTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
        this.callTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
    }.build().also {
        okHttpClient = it
    }

    override fun getNetworkClient(): Retrofit =
        if (this::retrofitClient.isInitialized) retrofitClient else buildRetrofitClient()


    private fun buildRetrofitClient(): Retrofit = Retrofit.Builder().apply {
        this.baseUrl(baseUrl)
        this.client(getOkHttpClient())
        this.addCallAdapterFactory(adapterFactory)
        this.addConverterFactory(converterFactory)
    }.build().also {
        retrofitClient = it
    }
}