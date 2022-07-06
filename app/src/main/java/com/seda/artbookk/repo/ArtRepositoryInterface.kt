package com.seda.artbookk.repo

import androidx.lifecycle.LiveData

import com.seda.artbookk.model.ImageResponse
import com.seda.artbookk.roomdb.Art
import com.seda.artbookk.unit.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>
}