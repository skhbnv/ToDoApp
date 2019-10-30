package com.example.myapplication.data.viewmodel

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repository.DatabaseRepository
import com.example.myapplication.models.CardItems

class ToDoViewModel(
    private val repository: DatabaseRepository
) : ViewModel() {
    val itemsLiveData = MutableLiveData<List<CardItems>>()

    fun insert(item: CardItems){
        AsyncTask.execute{
            repository.insert(item)
        }
    }

    fun getAllItems(){
        AsyncTask.execute{
            val items = repository.getAllItems()
            itemsLiveData.postValue(items)
        }
    }

    class Factory(
        private val repository: DatabaseRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return  ToDoViewModel(repository) as T
        }

    }
}