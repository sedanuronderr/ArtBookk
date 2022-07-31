package com.seda.artbookk.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seda.artbookk.model.ImageResponse
import com.seda.artbookk.repo.ArtRepositoryInterface
import com.seda.artbookk.roomdb.Art
import com.seda.artbookk.unit.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val repositoryInterface: ArtRepositoryInterface)
    : ViewModel(){

        val artList = repositoryInterface.getArt()

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList :LiveData<Resource<ImageResponse>>
    get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
    get() = selectedImage

    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = insertArtMsg

    fun setSelectedImage(url:String){
 selectedImage.value = url
        selectedImageUrl.value?.let { Log.e("ss", it) }
    }

    fun deleteArt(art: Art)=viewModelScope.launch{
        repositoryInterface.deleteArt(art)
    }
    fun insertArt(art: Art)=viewModelScope.launch{
        repositoryInterface.insertArt(art)
    }

    fun makeArt(name : String, artistName : String, year : String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty() ) {
            insertArtMsg.postValue(Resource.error("Enter name, artist, year", null))
            Log.e("hata","name yıl year")
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
           insertArtMsg.postValue(Resource.error("Year should be number",null))
            return
        }

        val art = Art(name, artistName, yearInt,selectedImage.value?: "")
        insertArt(art)
        setSelectedImage("")
        Log.e("başarı","basarı")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repositoryInterface.searchImage(searchString)
            images.value = response
        }

    }
}