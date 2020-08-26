package com.iherrera.chatkotlin.activities.services

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.iherrera.chatkotlin.activities.models.Movie
import com.iherrera.chatkotlin.activities.models.PopularMovieResponse
import com.iherrera.chatkotlin.activities.services.api.ITheMovieDBService
import com.iherrera.chatkotlin.activities.utilities.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repositorio que accederá a los servicios de la interface de The Movie DB
 */
class TheMovieDBRepository {
    /**
     * Servicios de The Movie DB
     *
     * @param {ITheMovieDBService?} theMovieDBService
     */
    private var theMovieDBService: ITheMovieDBService? = null

    /**
     * Cliente con Interceptor
     *
     * @param {HTTPClient?} httpClient
     */
    private var httpClient: HTTPClient? = null

    /**
     * Listado de Peliculas Populares
     *
     * @param {MutableLiveData<List<Movie>>?} popularMovies
     */
    var popularMovies: MutableLiveData<ArrayList<Movie>>? = null

    init {
        httpClient = HTTPClient.instance
        theMovieDBService = httpClient?.getTheMovieDBService()
        popularMovies = popularMovies()
    }

    /**
     * Obtiene el listado de Peliculas Populares
     *
     * @return {MutableLiveData<ArrayList<Movie>>?}
     */
    fun popularMovies(): MutableLiveData<ArrayList<Movie>>? {
        if (popularMovies == null)
            popularMovies = MutableLiveData<ArrayList<Movie>>()

        val call: Call<PopularMovieResponse>? = theMovieDBService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMovieResponse> {
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Toast.makeText(App.instance, "Error en la llamada", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful)
                    popularMovies?.value = response.body()?.results as ArrayList<Movie>?
            }

        })

        return popularMovies
    }
}