package com.seda.artbookk

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import dagger.hilt.android.AndroidEntryPoint

import java.io.IOException
import java.net.URI

class GlideLoader(val context: Context) {

    fun loadUserPicture(imageURI: Any, imageView: ImageView){
         try {
             Glide
                 .with(context)
                 .load(Uri.parse(imageURI.toString()))
                 .centerCrop()
                 .into(imageView)
         }catch (e:IOException){
             e.printStackTrace()
         }
    }


}