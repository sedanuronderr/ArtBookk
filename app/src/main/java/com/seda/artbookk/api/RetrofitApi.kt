package com.seda.artbookk.api


import com.seda.artbookk.model.ImageResponse
import com.seda.artbookk.unit.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery:String,
        @Query("key") apikey:String=API_KEY

        ):Response<ImageResponse>
}