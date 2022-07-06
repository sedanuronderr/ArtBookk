package com.seda.artbookk.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.seda.artbookk.R
import com.seda.artbookk.databinding.FragmentArtDetailsBinding
import com.seda.artbookk.databinding.FragmentImageApiBinding

class ImageApi : Fragment(R.layout.fragment_image_api){
    private lateinit var _binding: FragmentImageApiBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentImageApiBinding.bind(view)
        _binding = binding
    }
}