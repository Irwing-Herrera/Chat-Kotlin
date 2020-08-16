package com.iherrera.chatkotlin.activities.services.api

import com.iherrera.chatkotlin.activities.models.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface para peticiones HTTP
 */
interface ITheMovieDBService {

    /**
     * Obtener peliculas populares
     */
    @GET("/3/movie/popular")
    fun getPopularMovies(): Call<PopularMovieResponse>

}