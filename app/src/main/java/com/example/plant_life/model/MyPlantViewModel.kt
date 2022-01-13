package com.example.plant_life.model

import android.view.View
import android.widget.Toast
import com.example.plant_life.dataApi.ResponseItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyPlantViewModel {
    val MyPlantList = mutableListOf<ResponseItem>()
    fun loadMyPlant(): List<ResponseItem> {
        return MyPlantList
    }

    fun addMyPlant(item: ResponseItem) {
        if (MyPlantList.contains(item)) {
            MyPlantList.remove(item)
        } else {
            MyPlantList.add(item)

        }
    }




}