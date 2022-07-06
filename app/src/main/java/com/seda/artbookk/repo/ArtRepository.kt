package com.seda.artbookk.repo

import androidx.lifecycle.LiveData

import com.seda.artbookk.api.RetrofitApi
import com.seda.artbookk.model.ImageResponse
import com.seda.artbookk.roomdb.Art

import com.seda.artbookk.roomdb.ArtDao
import com.seda.artbookk.unit.Resource

import javax.inject.Inject
import kotlin.Result.Companion.success

class ArtRepository
@Inject constructor (
    private val artDao : ArtDao,
    private val retrofitApi : RetrofitApi
) :ArtRepositoryInterface {

    override suspend fun insertArt(art: Art) {
        artDao.instertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArt()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("No data!",null)
        }
    }
}