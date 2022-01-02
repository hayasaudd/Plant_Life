package com.example.plant_life.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.example.plant_life.LoginActivity
import com.example.plant_life.R
import com.example.plant_life.databinding.ActivityHomeBinding
import com.example.plant_life.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private val db = Firebase.firestore
    private lateinit var _binding: FragmentProfileBinding
    private val binding get() = _binding
    private val collection = Firebase.firestore.collection("user profiles")

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email= ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
        var id= FirebaseAuth.getInstance().currentUser?.uid?:""
// Add a new document with a generated ID
        db.collection("users/$id")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }

        //        handle click - logout--------------------------------------
        binding.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            val intent =
                Intent(this@ProfileFragment.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_profile, container, false)
    }


//    private fun chekUser() {
//        //check user is logged in or not
//        val firebaseUser= firebaseAuth.currentUser
//
//        if (firebaseUser != null)
//        { //user not null- user is logged in - get user info
//            val email = firebaseUser.email
////---------related to display the email of current user
//            //set to text view
//            binding.emailTv.text = email
//        }
//        else{
//            //user is null- user is not logged in - go to login activity
//            binding.logoutBtn.setOnClickListener {
//                val intent =
//                    Intent(this@ProfileFragment.requireContext(), LoginActivity::class.java)
//                startActivity(intent)
//            }
//
//        }
//    }

}