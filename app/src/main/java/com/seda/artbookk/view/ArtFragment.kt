package com.seda.artbookk.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seda.artbookk.R
import com.seda.artbookk.adapter.ArtRecyclerView
import com.seda.artbookk.databinding.FragmentArtsBinding
import com.seda.artbookk.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtFragment  :Fragment(R.layout.fragment__arts){
    private lateinit var binding:FragmentArtsBinding
    private val viewModel:ArtViewModel by viewModels()
    private  lateinit var artAdapter : ArtRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ? {
        // Inflate the layout for this fragment
        binding = FragmentArtsBinding.inflate(inflater, container, false)
        return binding.root



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
prepareRecycler()
        subscribeToObservers()

        }
    private fun prepareRecycler() {
        artAdapter = ArtRecyclerView()
        binding.recyclerViewArt.apply{
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter =artAdapter
        }
    }
    private fun subscribeToObservers(){
        viewModel.artList.observe(viewLifecycleOwner) {
            artAdapter.differ.submitList(it)
        }
    }

    }

