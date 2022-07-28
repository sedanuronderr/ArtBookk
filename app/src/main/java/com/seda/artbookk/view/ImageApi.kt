package com.seda.artbookk.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.seda.artbookk.R
import com.seda.artbookk.adapter.ArtRecyclerView
import com.seda.artbookk.adapter.ImageRecyclerAdapter
import com.seda.artbookk.databinding.FragmentArtDetailsBinding
import com.seda.artbookk.databinding.FragmentArtsBinding
import com.seda.artbookk.databinding.FragmentImageApiBinding
import com.seda.artbookk.unit.Status
import com.seda.artbookk.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageApi : Fragment(R.layout.fragment_image_api){
    private lateinit var binding: FragmentImageApiBinding
    private  lateinit var artAdapter : ImageRecyclerAdapter
    private val viewModel: ArtViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageApiBinding.inflate(inflater, container, false)
        return binding.root



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        prepareRecycler()
        subscribeToObservers()

artAdapter.setOnItemClickListener {
    viewModel.setSelectedImage(it)
    Log.e("cevap", it)

    }


        var job: Job? = null

        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }

    }

    private fun prepareRecycler() {
        artAdapter = ImageRecyclerAdapter()
        binding.imageRecyclerView.apply{
            layoutManager =   GridLayoutManager(requireContext(),3)
            adapter =artAdapter
        }
    }
    private fun subscribeToObservers(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult ->  imageResult.previewURL }
                    artAdapter.images = urls ?: listOf()

                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()

                }
                else -> {}
            }
            })
    }
}
