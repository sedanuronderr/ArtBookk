package com.seda.artbookk.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.seda.artbookk.repo.ArtRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val repositoryInterface: ArtRepositoryInterface)
    : ViewModel(){
}