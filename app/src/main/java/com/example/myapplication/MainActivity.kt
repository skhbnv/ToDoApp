package com.example.myapplication

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.MainPageAdapter
import com.example.myapplication.data.repository.DatabaseRepository
import com.example.myapplication.data.viewmodel.ToDoViewModel
import com.example.myapplication.models.CardItems
import com.example.roomapp.database.AppDatabase

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MainPageAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        itemViewModel.getAllItems()
    }

    override fun onResume() {
        super.onResume()
        itemViewModel.getAllItems()
    }

    private val itemViewModel by lazy {
        ViewModelProviders.of(this,
            ToDoViewModel.Factory(DatabaseRepository(
                AppDatabase.getInstance(applicationContext)?.getItemDao()
            )))[ToDoViewModel::class.java]
    }

    private fun initialize(){

        itemViewModel.itemsLiveData.observe(this, Observer {
            setRecyclerView()
            viewAdapter.initTheList(it)
        })

        fabOnClick()
    }

    private fun setRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = MainPageAdapter(
            onItemClick = { card ->
            val intent =  Intent(this, ToDoPageActivity::class.java)
            intent.putExtra("pageObject", card)
            startActivity(intent)
        })

        recyclerView = findViewById<RecyclerView>(R.id.recyclerMain).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


    private fun fabOnClick() {
        fab.setOnClickListener { _ ->
            val intent =  Intent(this, CreateNewItem::class.java)
            startActivity(intent)
        }
    }
}
