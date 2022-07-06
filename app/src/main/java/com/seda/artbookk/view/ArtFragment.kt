package com.seda.artbookk.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.seda.artbookk.R
import com.seda.artbookk.databinding.FragmentArtsBinding

class ArtFragment :Fragment(R.layout.fragment__arts){
    private lateinit var _binding:FragmentArtsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArtsBinding.bind(view)
        _binding = binding

        binding.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }

        val callback=object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }

        }

    }


}