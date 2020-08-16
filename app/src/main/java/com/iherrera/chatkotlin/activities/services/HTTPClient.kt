package com.iherrera.chatkotlin.activities.services

import com.iherrera.chatkotlin.activities.services.api.ITheMovieDBService
import com.iherrera.chatkotlin.activities.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Agregar Interceptor a Retrofit y crear cliente HTTP
 */
class HTTPClient {
    /**
     * Instancia de interfaz de servicios
     *
     * @property {ITheMovieDBService} theMovieDBService
     */
    private val theMovieDBService: ITheMovieDBService
    /**
     * Instancia de Retrofit
     *
     * @property {Retrofit} retrofit
     */
    private val retrofit: Retrofit

    companion object {
        var instance: HTTPClient? = null
            get() {
                if (field == null)
                    instance = HTTPClient()
                return field
            }
    }

    init {
        // Incluir el interceptor que hemos definido
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(HTTPInterceptor())

        val client = okHttpClientBuilder.build()

        // Construir el cliente de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.TMDB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        // Instanciamos el servicio de Retrofit a partir del objeto retrofit
        theMovieDBService = retrofit.create(ITheMovieDBService::class.java)
    }

    /**
     * Realizar la peticion de Peliculas Populares
     *
     * @return {Call<PopularMovieResponse>}
     */
    fun getTheMovieDBService() = theMovieDBService
}