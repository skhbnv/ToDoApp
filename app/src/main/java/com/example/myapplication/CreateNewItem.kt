package com.example.myapplication

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.data.repository.DatabaseRepository
import com.example.myapplication.data.viewmodel.ToDoViewModel
import com.example.myapplication.extensions.makeText
import com.example.myapplication.models.CardItems
import com.example.roomapp.database.AppDatabase
import kotlinx.android.synthetic.main.activity_create_new_item.*

class CreateNewItem : AppCompatActivity(), View.OnClickListener {
    private var itemPriority: String = "not labeled"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_item)
        setOnClicks()
    }
    private val itemViewModel by lazy {
        ViewModelProviders.of(this,
            ToDoViewModel.Factory(
                DatabaseRepository(
                    AppDatabase.getInstance(applicationContext)?.getItemDao()
                )
            ))[ToDoViewModel::class.java]
    }

    private fun setOnClicks() {
        lowButton.setOnClickListener(this)
        mediumButton.setOnClickListener(this)
        highButton.setOnClickListener(this)

        saveButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

            when(v?.id){
            lowButton.id -> {
                priorityIndicator.setTextColor(resources.getColor(R.color.lowUrgencyColor))
                priorityIndicator.setText(R.string.low)
                itemPriority = lowButton.text.toString()
            }
            mediumButton.id -> {
                priorityIndicator.setTextColor(resources.getColor(R.color.middleUrgencyColor))
                priorityIndicator.setText(R.string.medium)
                itemPriority = mediumButton.text.toString()
            }
            highButton.id -> {
                priorityIndicator.setTextColor(resources.getColor(R.color.highUrgencyColor))
                priorityIndicator.setText(R.string.high)
                itemPriority = highButton.text.toString()
            }

            //Save button is pressed
            saveButton.id ->{
                val objectTitle = titleNewItem.text.toString()
                val objectDesc = descriptionNewItem.text.toString()
                val objectDate = dateNewItem.text.toString()

                if (objectTitle != "" && objectDesc != "" && objectDate != ""){
                    createInRoom(CardItems(
                        title = objectTitle,
                        description = objectDesc,
                        date = objectDate,
                        urgency = itemPriority))
                }else{
                    makeText("empty fields")
                }
            }

        }
    }

    private fun createInRoom(cardItem: CardItems) {
        itemViewModel.insert(cardItem)
        makeText("Successfully added")
        finish()
    }
}
