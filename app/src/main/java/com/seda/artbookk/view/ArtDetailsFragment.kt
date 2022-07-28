package com.seda.artbookk.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.seda.artbookk.GlideLoader
import com.seda.artbookk.R
import com.seda.artbookk.databinding.FragmentArtDetailsBinding
import com.seda.artbookk.databinding.FragmentArtsBinding
import com.seda.artbookk.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtDetailsFragment : Fragment(R.layout.fragment_art_details){
    private lateinit var binding: FragmentArtDetailsBinding
    private val viewModel: ArtViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArtDetailsBinding.inflate(inflater, container, false)
        return binding.root



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApi())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


        binding.saveButton.setOnClickListener {
            viewModel.makeArt(binding.nameText.text.toString(),binding.artistText.text.toString(),binding.yearText.text.toString())
        }

    }
    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer {url->
            Log.e("image",url)
            GlideLoader(requireContext()).loadUserPicture(url,binding.artImageView)

        })
    }

}