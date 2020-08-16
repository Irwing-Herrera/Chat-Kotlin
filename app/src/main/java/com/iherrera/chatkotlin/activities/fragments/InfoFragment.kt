package com.iherrera.chatkotlin.activities.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.db.viewmodel.MovieViewModel
import com.iherrera.chatkotlin.activities.models.Movie
import com.iherrera.chatkotlin.activities.utilities.adapters.MovieAdapter
import com.iherrera.chatkotlin.activities.utilities.listeners.MovieListener
import kotlin.math.log

class InfoFragment : Fragment(), MovieListener {

    /**
     * ViewModel de Popular Movies
     *
     * @property {MovieViewModel} movieViewModel
     */
    private val movieViewModel: MovieViewModel by activityViewModels()
    /**
     * Listado de Peliculas Populares
     *
     * @property {List<Movie>} popularMoview
     */
    private var movies: ArrayList<Movie> = ArrayList()
    /**
     * Adapter de listado de peliculas
     *
     * @property {MovieRecyclerViewAdapter} movieAdapter
     */
    private val movieAdapter: MovieAdapter =
        MovieAdapter(this)
    /**
     * Columnas en pantalla
     *
     * @property {Int} columnCount
     */
    private var columnCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

        if (rootView is RecyclerView) {
            with(rootView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter
            }
        }

        loadPopularMovies()

        return rootView
    }

    private fun loadPopularMovies() {
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer<ArrayList<Movie>> {
            movies = it
            movieAdapter.setItems(movies)
        })
    }

    override fun onMovieSelected(movieId: Int) {
        movies.find {
            it.id == movieId
        }.let {
            Log.w("consola", it.toString())
        }
    }

}
