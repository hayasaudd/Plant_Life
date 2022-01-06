package com.example.plant_life.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.plant_life.R
import com.example.plant_life.databinding.FragmentMyPlantFragmentBinding
import com.example.plant_life.databinding.FragmentProfileBinding
import com.example.plant_life.databinding.FragmentUserProileInfoBinding
import com.example.plant_life.model.PlantViewModel


class UserProileInfoFragment : Fragment() {
    private val viewModel: PlantViewModel by viewModels()
    var _binding: FragmentUserProileInfoBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProileInfoBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.info = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TAG", "onViewCreated: in")
        viewModel.showUserInfo()

        binding?.EditButton?.setOnClickListener {
            val action =
                UserProileInfoFragmentDirections.actionUserProileInfoFragmentToProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}