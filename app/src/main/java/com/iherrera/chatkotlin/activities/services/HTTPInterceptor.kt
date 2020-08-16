package com.iherrera.chatkotlin.activities.services

import com.iherrera.chatkotlin.activities.utilities.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor de Peticiones HTTP para Teh Movie
 */
class HTTPInterceptor : Interceptor {

    /**
     * Agrega parametros a las peticiones HTTP solicitadas
     *
     * @param {Interceptor.Chain} chain
     * @return {Response}
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        // Agregar params a URL de peticion
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.API_KEY)
            .addQueryParameter(Constants.URL_PARAM_LANGUAGE, "es-ES")
            .build()

        //Agregar Headers a peticiones HTTP
        var request = chain.request()

        request = request.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }

}