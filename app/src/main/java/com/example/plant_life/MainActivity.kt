package com.example.plant_life

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plant_life.databinding.ActivityLoginBinding
import com.example.plant_life.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))

        }

        //handel click alredy have acount
        binding.haveAccount.setOnClickListener{
            //validate data
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}