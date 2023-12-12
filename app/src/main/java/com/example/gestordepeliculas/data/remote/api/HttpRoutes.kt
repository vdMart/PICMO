package com.example.gestordepeliculas.data.remote.api

object HttpRoutes {
    private const val BASE_URL = "https://api.themoviedb.org/3"
    private const val DISCOVER = "/discover"
    private const val POPULAR = "/popular"
    private const val SEARCH = "/search"
    private const val TYPE_MOVIE = "/movie"
    private const val TYPE_SERIE = "/tv"
    private const val TYPE_PERSON = "/person"
    private const val QUERY = "&query="
    private const val ITEM = "/videos"
    private const val PROVIDERS = "/watch/providers"
    const val API_KEY = "?api_key=bc403d6b7bd55f82c17aecec242b2c9f"


    /* MOVIE */
    //getMovieById(idMovie: Int) - https://api.themoviedb.org/3/movie/{movie_id}?api_key={API_KEY}
    const val MOVIE_BY_ID = BASE_URL + TYPE_MOVIE // + {movie_id} + API_KEY

    //findMovies(query: String)  - https://api.themoviedb.org/3/search/movie?api_key={API_KEY}&query={Jack+Reacher}'
    const val FIND_MOVIE_BY_QUERY = BASE_URL + SEARCH + TYPE_MOVIE + API_KEY + QUERY // + {query}

    //getMovies()                - https://api.themoviedb.org/3/discover/movie?api_key={API_KEY}
    const val MOVIE_DISCOVER = BASE_URL + DISCOVER + TYPE_MOVIE + API_KEY

    //getProvidersByIDMovie      - https://api.themoviedb.org/3/movie/{movie_id}/watch/providers
    const val MOVIE_PROVIDERS1 = BASE_URL + TYPE_MOVIE // + SERIE_ID + API_KEY
    const val MOVIE_PROVIDERS2 = PROVIDERS + API_KEY

    //getMovieTrailer(idMovie:Int)-https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key={API_KEY}
    const val TRAILER_MOVIE1 = "${BASE_URL}${TYPE_MOVIE}" // + MOVIE_ID
    const val TRAILER_MOVIE2 = "${ITEM}${API_KEY}"


    /* SERIE */
    //getSerieById(idSerie: Int) - https://api.themoviedb.org/3/tv/{series_id}?api_key={API_KEY}
    const val SERIE_BY_ID = BASE_URL + TYPE_SERIE

    //findSeries(query: String)  - https://api.themoviedb.org/3/search/tv?api_key={API_KEY}&query={Jack+Reacher}'
    const val FIND_SERIE_BY_QUERY = BASE_URL + SEARCH + TYPE_SERIE + API_KEY + QUERY // + {query}

    //getSeries()                - https://api.themoviedb.org/3/discover/tv?api_key={API_KEY}
    const val SERIE_DISCOVER = BASE_URL + DISCOVER + TYPE_SERIE + API_KEY

    //getProvidersByIDSerie      - https://api.themoviedb.org/3/tv/{series_id}/watch/providers
    const val SERIE_PROVIDERS1 = BASE_URL + TYPE_SERIE // + SERIE_ID +
    const val SERIE_PROVIDERS2 = PROVIDERS + API_KEY


    /* PERSON */
    //getPersonById              - https://api.themoviedb.org/3/person/{person_id}?api_key={API_KEY}
    const val PERSON_BY_ID = BASE_URL + TYPE_PERSON // + PERSON_ID + API_KEY

    //findPerson(query: String)  - https://api.themoviedb.org/3/search/movie?api_key={API_KEY}&query={Jack+Reacher}'
    const val FIND_PERSON_BY_QUERY = BASE_URL + SEARCH + TYPE_PERSON + API_KEY + QUERY // + {query}

    //getPersonPopular           - https://api.themoviedb.org/3/person/popular?api_key=bc403d6b7bd55f82c17aecec242b2c9f
    const val PERSON_POPULAR = BASE_URL + TYPE_PERSON + POPULAR + API_KEY
}

