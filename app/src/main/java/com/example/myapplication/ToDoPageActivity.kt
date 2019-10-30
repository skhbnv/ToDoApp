package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.models.CardItems
import kotlinx.android.synthetic.main.activity_to_do_page.*

class ToDoPageActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var extraObjects: CardItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_page)
        setListeners()
        setUI()
    }

    private fun setListeners() {
        markAsDoneButton.setOnClickListener(this)
        editButton.setOnClickListener(this)
    }

    private fun setUI() {
        val priority: String = "Priority: " + getExtraObject()?.urgency
        textDescription.text = getExtraObject()?.description
        textTitle.text = getExtraObject()?.title
        dueDatePage.text = getExtraObject()?.date
        priorityIndicator.text = priority

    }

    private fun getExtraObject(): CardItems? {
        return intent.getParcelableExtra("pageObject")
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            markAsDoneButton.id -> {

            }
            editButton.id -> {

            }
        }
    }
}
