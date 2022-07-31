package com.seda.artbookk.viewmodel

import com.seda.artbookk.repo.FakeArtRepository
import org.junit.Before
import org.junit.Test

class viewmodeltest {
    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        //fake repository = test doubles
        viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.makeArt("Monalisaa","davinci","")
       val value = viewModel.insertArtMessage

    }

    @Test
    fun `insert art without name returns error`(){

    }
    @Test
    fun `insert art without artistName returns error`(){

    }

}