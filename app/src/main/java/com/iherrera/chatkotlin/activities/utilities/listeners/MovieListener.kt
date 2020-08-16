package com.iherrera.chatkotlin.activities.utilities.listeners

/**
 * Listeners al realizar una accion en un item de la lista de Peliculas
 */
interface MovieListener {
    /**
     * Seleccionar una pelicula
     *
     * @param {Int} movieId
     */
    fun onMovieSelected(movieId: Int) {}
}