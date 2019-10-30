package com.example.myapplication.data.repository

import com.example.myapplication.database.dao.DaoItems
import com.example.myapplication.models.CardItems

class DatabaseRepository(private val itemDao: DaoItems?) {
    fun insert(item: CardItems) {
        itemDao?.insert(item)
    }
    fun getAllItems() = itemDao?.getAllItems()
    fun getItemById(id: Int) = itemDao?.getItemById(id)
}