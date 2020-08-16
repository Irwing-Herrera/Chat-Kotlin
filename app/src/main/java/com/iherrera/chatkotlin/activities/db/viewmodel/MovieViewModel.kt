package com.iherrera.chatkotlin.activities.db.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iherrera.chatkotlin.activities.models.Movie
import com.iherrera.chatkotlin.activities.services.TheMovieDBRepository

/**
 * View Model que instanciará los metodos de la clase TheMovieDBRepository.
 */
class MovieViewModel : ViewModel() {
    /**
     * Repositorio
     *
     * @property {TheMovieDBRepository} theMovieDBRepository
     */
    private var theMovieDBRepository = TheMovieDBRepository()
    /**
     * Listado de Peliculas Populares
     *
     * @property {MutableLiveData<ArrayList<Movie>>} popularMovie
     */
    private var popularMovie = theMovieDBRepository.popularMovies()!!

    /**
     * Obtener Peliculas Populares
     *
     * @return {LiveData<List<Movie>>}
     */
    fun getPopularMovies(): LiveData<ArrayList<Movie>> {
        return popularMovie
    }
}