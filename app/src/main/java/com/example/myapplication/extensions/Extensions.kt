package com.example.myapplication.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.makeText(message :String){
    Toast.makeText(this,message ,Toast.LENGTH_LONG).show()
}