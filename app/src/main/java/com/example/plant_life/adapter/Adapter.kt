package com.example.plant_life.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.plant_life.R
import com.example.plant_life.dataApi.ResponseItem
import com.example.plant_life.databinding.FragmentDitealsPlantPageBinding
import com.example.plant_life.databinding.ItemStyleBinding
import com.example.plant_life.fragments.HomeFragmentDirections
import com.example.plant_life.model.PlantViewModel.Companion.favPlantList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PlantAdapter(private val context: Context, val chekItem: Boolean) :
    ListAdapter<ResponseItem, PlantAdapter.PlantInfoViewHolder>(DiffCallback) {

    inner class PlantInfoViewHolder(
        private var binding:
        ItemStyleBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val card: CardView = binding.cardView
        val buttonShare = binding.buttonShare
        val alarmButton = binding.alarmButton
        lateinit var _binding: FragmentDitealsPlantPageBinding
        private val myPlantCollection = Firebase.firestore.collection("User profiles")


        fun bind(resultsItems: ResponseItem) {
            binding.plantInfo = resultsItems


            if (chekItem == true) {

            }
            for (b in favPlantList.MyPlantList) {
                if (b.id == resultsItems.id && resultsItems.isFavPlant) {
                    binding.likeImg.setImageResource(R.drawable.favorite_filled)
                }
            }

            //  Log.e("TAG", "bind: it_id ${it.id} : resultsItems.id ${resultsItems.id} boolean ${it.id == resultsItems.id}  resultsItems.isFavPlant ${resultsItems.isFavPlant}", )

//if (!resultsItems.isFavPlant) {

            binding.executePendingBindings()
            //button to add plant to "my plant"
            binding?.likeImg?.setOnClickListener {

//                var s =
//                    favPlantList.MyPlantList.find { it.id == resultsItems.id } //check if the plant it's inn MyPlant list don't but it again
//                if (s == null) {
//                    favPlantList.MyPlantList.add(resultsItems)// add the plant in MyPlant List
//                    myPlantCollection.document("${FirebaseAuth.getInstance().currentUser?.uid ?: ""}")
//                        .update("my_planet", favPlantList.MyPlantList)
//
//                        .addOnCompleteListener {
//                            Log.d("TAG", "bind: ${it.isSuccessful} ${it.result.toString()}")
//                        }
//                } else {
//
//                }

                Log.e("TAG", "bind: ${resultsItems.isFavPlant}")

                Log.e("TAG", "bind: size:  ${favPlantList.MyPlantList.size}")
                var s = favPlantList.MyPlantList.find { it.id == resultsItems.id }

//                        favPlantList.MyPlantList.find {
//                            if(it.id == resultsItems.id){
//                                Log.e("TAG", "bind: is fav", )
//                            }else{
//
//                                Log.e("TAG", "bind: is NOOOOOOOOt fav", )
//                            }
//                            it.id == resultsItems.id
//                            } //check if the plant it's inn MyPlant list don't but it again
//                    Log.e("TAG", "bind: $s" )

                if (s == null) {

                    Log.e("TAG", "bind:  ${resultsItems}")
                    binding.likeImg.setImageResource(R.drawable.favorite_filled)
                    resultsItems.isFavPlant = true

                    favPlantList.MyPlantList.add(resultsItems)// add the plant in MyPlant List
                    myPlantCollection.document("${FirebaseAuth.getInstance().currentUser?.uid ?: ""}")
                        .update("my_planet", favPlantList.MyPlantList)

                        .addOnCompleteListener {
                            Log.d("TAG", "bind: ${it.isSuccessful} ${it.result.toString()}")
                        }

                } else {
                    favPlantList.MyPlantList.removeAll { it.id == resultsItems.id }
                    myPlantCollection.document("${FirebaseAuth.getInstance().currentUser?.uid ?: ""}")
                        .update("my_planet", favPlantList.MyPlantList)
                    binding.likeImg.setImageResource(R.drawable.favorite_border)
                    // resultsItems.isFavPlant = !resultsItems.isFavPlant
                }

                Log.d("adapter after", "${favPlantList.loadMyPlant()}")
                //  }
//                if (resultsItems.isFavPlant) {
//                    binding.likeImg.setImageResource(R.drawable.favorite_filled)
//                }
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
        //hold the card of more info for plant list "after that i created argument for home fragment in nav_graf"
        holder.card.setOnClickListener {
            Log.e("TAG", "id view :${position}")
            val action = HomeFragmentDirections.actionHomeFragmentToDitealsPlantPage(position)
            holder.itemView.findNavController().navigate(action)

        }

        holder.buttonShare?.setOnClickListener {

            Log.e("TAG", "onBindViewHolder: adapter")
            shared(resultsItems)
        }


    }


    companion object DiffCallback : DiffUtil.ItemCallback<ResponseItem>() {
        override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem.image == newItem.image
        }

    }

    fun shared(resultsItems: ResponseItem) {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")

            // (Optional) Here we're setting the title of the content
            putExtra(Intent.EXTRA_TITLE, "Introducing content previews")

                .putExtra(Intent.EXTRA_TEXT, "see my new plant ${resultsItems.image} ")
                .setType("text/plain")


        }, null)
        context.startActivity(share)

    }

    fun checkPlantInFavList(item: ResponseItem): Boolean {
        if (item != null) {
            favPlantList.MyPlantList.find { it.id == item.id }
            return true
        }//check if the plant it's inn MyPlant list don't but it again
        else {
            return false
        }
    }


}