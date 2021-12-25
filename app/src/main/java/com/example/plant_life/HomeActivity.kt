package com.example.plant_life

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.plant_life.databinding.ActivityProfileBinding
import com.example.plant_life.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivityProfileBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email= ""
    private var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //configure ActionBar
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        chekUser()

        //handle click - logout
        binding.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            chekUser()
        }
    }

    private fun chekUser() {
        //check user is logged in or not
        val firebaseUser= firebaseAuth.currentUser

        if (firebaseUser != null)
        {
            //user not null- user is logged in - get user info
            val email = firebaseUser.email

            //set to text view
            binding.emailTv.text = email
        }
        else{
            //user is null- user is not logged in - go to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }
    }
}