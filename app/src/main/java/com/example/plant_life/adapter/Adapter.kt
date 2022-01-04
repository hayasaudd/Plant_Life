package com.example.plant_life.adapter



import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.plant_life.fragments.HomeFragmentArgs
import com.example.plant_life.fragments.HomeFragmentDirections
import com.example.plant_life.fragments.MyPlant_fragmentDirections
import com.example.plant_life.model.PlantViewModel.Companion.favPlantList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PlantAdapter():ListAdapter<ResponseItem,PlantAdapter.PlantInfoViewHolder>(DiffCallback) {
    lateinit var context: Context
    lateinit var action: NavDirections

    inner class PlantInfoViewHolder(private var binding:
                                    ItemStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val card: CardView = binding.cardView


        private val myPlantCollection = Firebase.firestore.collection("User profiles")




       //val userId = auth.currentUser?.userId
        fun bind(resultsItems: ResponseItem) {
            binding.plantInfo = resultsItems
            binding.executePendingBindings()

           //button to add plant to "my plant"
            binding.addToMyPlantButton.setOnClickListener {
         var s=     favPlantList.MyPlantList.find { it.id==resultsItems.id } //check if the plant it's inn MyPlant list don't but it again
                if(s==null) {
                    favPlantList.MyPlantList.add(resultsItems)// add the plant in MyPlant List
                    myPlantCollection.document("${FirebaseAuth.getInstance().currentUser?.uid ?: ""}")
                        .update("my_planet", favPlantList.MyPlantList)

                        .addOnCompleteListener {
                            Log.d("TAG", "bind: ${it.isSuccessful} ${it.result.toString()}")
                        }
                }else{

//                 Toast.makeText(context, "already have this plant in My Plant list", Toast.LENGTH_SHORT)
                }

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