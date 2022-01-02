package com.example.plant_life.model

import android.view.View
import android.widget.Toast
import com.example.plant_life.dataApi.ResponseItem

class MyPlantViewModel {
    val MyPlantList = mutableListOf<ResponseItem>()
    fun loadMyPlant(): List<ResponseItem> {
        return MyPlantList
    }

    fun addMyPlant(item: ResponseItem, view: View?) {
        if (MyPlantList.contains(item)) {
            MyPlantList.remove(item)
            Toast.makeText(view?.context, "${item.PlantName} remove from favourite", Toast.LENGTH_SHORT)
                .show()
        } else {
            MyPlantList.add(item)
            Toast.makeText(view?.context, "${item.PlantName} add to favourite", Toast.LENGTH_SHORT)
                .show()
        }
    }
}