package com.example.plant_life.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.plant_life.R
import com.example.plant_life.adapter.PlantAdapter
import com.example.plant_life.databinding.FragmentHomeFragmentBinding
import com.example.plant_life.databinding.ItemStyleBinding
import com.example.plant_life.model.PlantViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log


class HomeFragment : Fragment() {

private val plantViewModel: PlantViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeFragmentBinding.inflate(inflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
        plantViewModel.getplantInfo()
        binding?.apply {
            Log.e("TAG", "onCreateView:  binding?.apply ${plantViewModel?.state.value}"  )
            lifecycleOwner = viewLifecycleOwner
            viewModel = plantViewModel
            recyclerView?.adapter = PlantAdapter()
        }



        setHasOptionsMenu(true)
        return binding?.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_home_fragment, container, false)

//        val action= Add_New_TaskDirections.actionAddNewTaskToFirstpagefortak()
//        findNavController().navigate(action)



    }




    }

