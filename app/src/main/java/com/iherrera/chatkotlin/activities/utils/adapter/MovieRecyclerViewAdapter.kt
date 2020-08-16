package com.iherrera.chatkotlin.activities.utils.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import coil.transform.CircleCropTransformation
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.models.Movie
import com.iherrera.chatkotlin.activities.utils.Constants
import kotlinx.android.synthetic.main.fragment_info.view.*

/**
 * Adaptador de Pelicula
 */
class MovieRecyclerViewAdapter : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    /**
     * Evento click de Pelicula
     *
     * @property {View.OnClickListener} mOnClickListener
     */
    private val mOnClickListener: View.OnClickListener
    /**
     * Listado de Peliculas
     *
     * @property {View.List<Movie>} movies
     */
    private var movies: List<Movie> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            Log.w("consola", item.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.title.text = item.title
        holder.ranking.text = item.vote_average.toString()

        holder.poster.load(Constants.IMAGE_BASE_URL + item.poster_path) {
            crossfade(true)
            placeholder(R.drawable.loading)
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val poster = mView.imageViewPoster
        val title = mView.textViewTitle
        val ranking = mView.textViewRating

    }
}