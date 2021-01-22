package com.bayuokta.core.data.source.remote.network

import com.bayuokta.core.data.source.remote.response.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/now_playing?api_key=6a83fe4d624ebb1804c7cab0d47c978a")
    suspend fun getMovie(): Response
}
