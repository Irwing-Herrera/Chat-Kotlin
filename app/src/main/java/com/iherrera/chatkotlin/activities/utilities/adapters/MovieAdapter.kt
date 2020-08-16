package com.iherrera.chatkotlin.activities.utilities.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.models.Movie
import com.iherrera.chatkotlin.activities.utilities.Constants
import com.iherrera.chatkotlin.activities.utilities.inflate
import com.iherrera.chatkotlin.activities.utilities.listeners.MovieListener
import kotlinx.android.synthetic.main.fragment_info.view.*

class MovieAdapter(private val listener: MovieListener? = null) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    /**
     * Inicalizar listado de peliculas
     *
     * @property {ArrayList<Movie>} movies
     */
    private var movies: ArrayList<Movie>? = null

    /**
     * Llenar listado de peliculas
     *
     * @param {ArrayList<Movie>} movies
     */
    fun setItems(movies: ArrayList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    /**
     * Obtener fragment a llenar
     *
     * @param {ViewGroup} parent
     * @param {Int} viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieRow = parent.inflate(R.layout.fragment_info)
        return MovieViewHolder(
            movieRow,
            listener
        )
    }

    /**
     * Obtener el tamaño de la lista
     *
     * @return {Int}
     */
    override fun getItemCount(): Int = movies?.size ?: 0

    /**
     * Llenar fragment con valores de una pelicula
     *
     * @param {MovieViewHolder} holder
     * @param {Int} position
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.get(position).let {
            holder.apply {
                title.text = it?.title
                ranking.text = it?.vote_average.toString()
                poster.load(Constants.IMAGE_BASE_URL + it?.poster_path) {
                    crossfade(true)
                    placeholder(R.drawable.loading)
                    transformations(CircleCropTransformation())
                }
                movie = it!!
            }
        }
    }

    /**
     * Obtener UI y declarar listeners
     *
     * @param {View} view
     */
    class MovieViewHolder(view: View, private val listener: MovieListener?) : RecyclerView.ViewHolder(view) {
        lateinit var movie: Movie

        val poster: ImageView = view.imageViewPoster
        val title: TextView = view.textViewTitle
        val ranking: TextView = view.textViewRating

        private val selectMovie: ConstraintLayout = view.findViewById(R.id.selectMovie)

        init {
            selectMovie.setOnClickListener {
                listener?.onMovieSelected(movie.id)
            }
        }
    }
}