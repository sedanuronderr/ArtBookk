package com.seda.artbookk.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.seda.artbookk.MainCoroutineRule
import com.seda.artbookk.getOrAwaitValueTest
import com.seda.artbookk.repo.FakeArtRepository
import com.seda.artbookk.unit.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class viewmodeltest {
    @get:Rule
    // anlık kulları sergilemek yapmak.Main threadde çalıştırmamızı sağlıyor
    val instantTaskExecutorRule =InstantTaskExecutorRule()
@get:Rule
val mainCoroutineRule =MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel


    @Before
    fun setup(){
        //fake repository = test doubles
        viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.makeArt("Monalisaa","davinci","")
       val value = viewModel.insertArtMessage.getOrAwaitValueTest()
assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name returns error`(){

    }
    @Test
    fun `insert art without artistName returns error`(){

    }

}