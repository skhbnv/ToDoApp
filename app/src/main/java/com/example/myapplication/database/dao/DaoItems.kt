package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.CardItems

@Dao
interface DaoItems {

    @Insert
    fun insert(item: CardItems)

    @Query("SELECT * FROM carditems")
    fun getAllItems(): List<CardItems>

    @Query("SELECT * FROM CardItems WHERE id = :id")
    fun getItemById(id: Int): CardItems

}