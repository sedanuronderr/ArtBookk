package com.seda.artbookk.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.seda.artbookk.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest //Unit test  Mediumtest = integrationtest largetest =UI test
@ExperimentalCoroutinesApi
class ArtDaoTest {
    @get:Rule
    //Main threadde çalıştırmamızı sağlar
    var instantTaskExecutorRule =InstantTaskExecutorRule()

    private lateinit var dao: ArtDao
    private lateinit var database: ArtDatabase

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),ArtDatabase::class.java)
            .allowMainThreadQueries().build()
dao =database.artDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertArttesting() =runBlockingTest{
        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.instertArt(exampleArt)

        val list = dao.observeArt().getOrAwaitValue()
        assertThat(list).contains(exampleArt)
    }
    @Test
    fun deleteArtTesting() = runBlockingTest {
        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.instertArt(exampleArt)
        dao.deleteArt(exampleArt)

        val list = dao.observeArt().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)

    }
}