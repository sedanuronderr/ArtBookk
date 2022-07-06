package com.seda.artbookk.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtDao {
    //id ler çakışırsa ne olur.İgnore=gözardı.replace=yerine yazar
    @Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun instertArt(art:Art)

@Delete
suspend fun deleteArt(art: Art)

//Livedata asekron çalışır
@Query("SELECT * FROM arts")
fun observeArt():LiveData<List<Art>>

}