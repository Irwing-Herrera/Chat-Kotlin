package com.iherrera.chatkotlin.activities.utils

/**
 * Clase de Constantes
 */
class Constants {
    companion object {
        /**
         * URL Base de peticiones de API
         *
         * @property {String}
         */
        const val TMDB_API_BASE_URL = "https://api.themoviedb.org"
        /**
         * Llave necesaria para hcer peticiones en la API
         *
         * @property {String}
         */
        const val API_KEY = "08675fe7f509a20bcf81c8b69a67579f"
        /**
         * URL Base de peticion de imagenes de API
         *
         * @property {String}
         */
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        /**
         * Parametro de llave de URL de peticion de API
         *
         * @property {String}
         */
        const val URL_PARAM_API_KEY = "api_key"
        /**
         * Parametro de lenguaje de URL de peticion de API
         *
         * @property {String}
         */
        const val URL_PARAM_LANGUAGE = "language"
    }
}