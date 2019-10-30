package com.example.myapplication.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "CardItems")
data class CardItems(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
    @ColumnInfo val date: String,
    @ColumnInfo val urgency: String
) :Parcelable
