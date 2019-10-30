package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CardItems
import kotlinx.android.synthetic.main.todo_item_card.view.*

class MainPageAdapter(
    var itemsList: ArrayList<CardItems> = ArrayList(),
    var onItemClick: (CardItems) -> Unit) :
    RecyclerView.Adapter<MainPageAdapter.MyViewHolder>() {

    val highString = "High"
    val mediumString = "Medium"
    val lowString = "Low"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(p0: View?) {
            onItemClick(itemsList[adapterPosition])
        }

        private val cardTitle = itemView.titleCard
        private val cardDescription = itemView.descriptionCard
        private val cardDate = itemView.dueDateCard
        private val cardUrgencyIndicator = itemView.urgencyIndicator

        fun bind(cardItem: CardItems) {
            with(itemView) {
                titleCard.text = cardItem.title
            }
            cardTitle.text = cardItem.title
            cardDescription.text = cardItem.description
            cardDate.text = cardItem.date
            itemView.setOnClickListener(this)

            when(cardItem.urgency){
                highString -> cardUrgencyIndicator.
                    setBackgroundResource(R.color.highUrgencyColor)
                mediumString -> cardUrgencyIndicator.
                    setBackgroundResource(R.color.middleUrgencyColor)
                lowString -> cardUrgencyIndicator.
                    setBackgroundResource(R.color.lowUrgencyColor)
            }
            cardUrgencyIndicator.invalidate()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MainPageAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item_card, parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun initTheList(itemsList: List<CardItems>){
        with(this.itemsList) {
            clear()
            addAll(itemsList)
        }
    }
    override fun getItemCount() = itemsList.size
}
