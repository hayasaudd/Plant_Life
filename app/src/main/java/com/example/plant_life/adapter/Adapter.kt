package com.example.plant_life.adapter



import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.plant_life.R
import com.example.plant_life.dataApi.ResponseItem
import com.example.plant_life.databinding.ItemStyleBinding
import com.example.plant_life.fragments.HomeFragment
import com.example.plant_life.fragments.HomeFragmentDirections
import com.example.plant_life.fragments.MyPlant_fragmentDirections
import com.example.plant_life.model.PlantViewModel.Companion.favPlantList


class PlantAdapter(var flag :String):ListAdapter<ResponseItem,PlantAdapter.PlantInfoViewHolder>(DiffCallback) {

    lateinit var action: NavDirections

    inner class PlantInfoViewHolder(private var binding:
                                    ItemStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val card: CardView = binding.cardView

        fun bind(resultsItems: ResponseItem) {
            binding.plantInfo = resultsItems
            binding.executePendingBindings()
//            binding.plantImage.setOnClickListener {
//                if (flag == "home") {
//                    action = HomeFragmentDirections.actionHomeFragmentToDitealsPlantPage(
//                        resultsItems.PlantName,
//                        resultsItems.image,
//                        resultsItems.Watering,
//                        resultsItems.Temperature,
//                        resultsItems.watarAlarm,
//                        resultsItems.backgroundImage,
//                        resultsItems.sparingAlarm,
//                        resultsItems.id
//                    )
//                } else {
//                    action =
//                        MyPlant_fragmentDirections.actionMayPlantFragmentToDitealsPlantPage(
//                            resultsItems.PlantName,
//                            resultsItems.image,
//                            resultsItems.Watering,
//                            resultsItems.Temperature,
//                            resultsItems.watarAlarm,
//                            resultsItems.backgroundImage,
//                            resultsItems.sparingAlarm,
//                            resultsItems.id
//                        )
//                }
//                binding.root.findNavController()
//                    .navigate(action)
//            }
            binding.addToMyPlantButton.setOnClickListener {
                favPlantList.MyPlantList.add(resultsItems)

            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlantInfoViewHolder {
        return PlantInfoViewHolder(ItemStyleBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: PlantInfoViewHolder, position: Int) {
        val resultsItems = getItem(position)

        holder.bind(resultsItems)
        holder.card.setOnClickListener {
            Log.e("TAG","id view :${position}")
//to do any action

        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ResponseItem>(){
        override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem.image == newItem.image
        }

    }
}