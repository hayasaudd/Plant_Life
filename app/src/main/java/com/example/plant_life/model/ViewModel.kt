package com.example.plant_life.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plant_life.dataApi.PlantApi
import com.example.plant_life.dataApi.PlantApiService
import com.example.plant_life.dataApi.ResponseItem
import com.example.plant_life.dataApi.Response
import com.example.plant_life.fragments.MyPlant_fragment
import kotlinx.coroutines.launch

enum class PlantApiStatus {LOADING, ERROR, DONE}

class PlantViewModel: ViewModel() {
    private val _status = MutableLiveData<PlantApiStatus>()
    val status: LiveData<PlantApiStatus> = _status


    private val _state = MutableLiveData<String>()
    val state: LiveData<String> = _state

    private val _plantInfo = MutableLiveData<List<ResponseItem?>>()
    val plantInfo:LiveData<List<ResponseItem?>> = _plantInfo

    private val _response = MutableLiveData<Response?>()
    val response:LiveData<Response?> = _response

    private var _plantImage = MutableLiveData<String>()
    val plantImage: MutableLiveData<String> get() = _plantImage

    private var _plantImageBackground = MutableLiveData<String>()
    val plantImageBackground: MutableLiveData<String> get() = _plantImageBackground

    val _plantName = MutableLiveData<String>()
    val plantName: MutableLiveData<String> get() = _plantName

    val _Watering = MutableLiveData<String>()
    val Watering: MutableLiveData<String> get() = _Watering

    val _Lighting = MutableLiveData<String>()
    val Lighting: MutableLiveData<String> get() = _Lighting

    val _Temperature = MutableLiveData<String>()
    val Temperature: MutableLiveData<String> get() = _Temperature
    init {
        getplantInfo()
    }

    fun getplantInfo() {
        viewModelScope.launch {
            _status.value = PlantApiStatus.LOADING
            try {
                _plantInfo.value = PlantApi.retrofitServer.getInfo().response
               // _state.value = PlantApi.retrofitServer.getInfo().response.toString()
                Log.e("TAG", "getplantInfo: ${  _plantInfo.value }", )
              //  _status.value = PlantApiStatus.DONE
            }catch (e:Exception){
                Log.e("TAG", "getplantInfo:  error${  e }", )

                _status.value=PlantApiStatus.ERROR
                _plantInfo.value = listOf()
            }
        }

    }



}