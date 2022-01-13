package com.example.plant_life.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.plant_life.R
import com.example.plant_life.dataApi.ResponseItem
import com.example.plant_life.databinding.FragmentDitealsPlantPageBinding
import com.example.plant_life.databinding.FragmentUserProileInfoBinding
import com.example.plant_life.model.PlantViewModel

class DitealsPlantPage :Fragment(){
    private val viewModel: PlantViewModel by activityViewModels()
    var _binding: FragmentDitealsPlantPageBinding? = null
    private val binding get() = _binding
    var plant = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            plant=it!!.getInt("id")
            Log.e("TAG","id:${plant}")
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding = FragmentDitealsPlantPageBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel1 = viewModel

        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG","idMovie:${plant}")
        viewModel.plantInfoData(plant)

//button to share
        _binding?.buttonShare?.setOnClickListener {
//            val intent = Intent(Intent.ACTION_SEND)
//                .putExtra(Intent.EXTRA_TEXT,"See this plant that name ${viewModel.plantName.value}")
//                .setType("text/plain")
//
//            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
//                startActivity(intent)
//            }
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }
            startActivity(sendIntent)

        }

    }



}