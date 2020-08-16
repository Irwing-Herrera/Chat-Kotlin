package com.iherrera.chatkotlin.activities.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity
import com.iherrera.chatkotlin.activities.db.viewmodel.MovieViewModel
import com.iherrera.chatkotlin.activities.db.viewmodel.NoteViewModel
import com.iherrera.chatkotlin.activities.models.Movie
import com.iherrera.chatkotlin.activities.utils.adapter.MovieRecyclerViewAdapter

class InfoFragment : Fragment() {

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
    private var popularMovies: List<Movie> = ArrayList()
    /**
     * Adapter de listado de peliculas
     *
     * @property {MovieRecyclerViewAdapter} movieAdapter
     */
    private val movieAdapter: MovieRecyclerViewAdapter by lazy { MovieRecyclerViewAdapter() }
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

        // Observer de las peliculas
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer<List<Movie>> {
            popularMovies = it
            movieAdapter.setData(popularMovies)
        })

        return rootView
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}