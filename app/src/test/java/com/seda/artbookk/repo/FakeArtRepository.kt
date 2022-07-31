package com.seda.artbookk.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seda.artbookk.model.ImageResponse
import com.seda.artbookk.roomdb.Art
import com.seda.artbookk.unit.Resource

class FakeArtRepository : ArtRepositoryInterface {
    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
arts.add(art)
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
    }

    override fun getArt(): LiveData<List<Art>> {
return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {

        return  Resource.success(ImageResponse(listOf(),0,0))
    }
}