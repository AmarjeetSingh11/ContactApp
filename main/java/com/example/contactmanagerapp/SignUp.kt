package com.example.contactmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactmanagerapp.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signUpButton=findViewById<Button>(R.id.btnSignUp)

        val etName=findViewById<TextInputEditText>(R.id.etName)
        val etMail=findViewById<TextInputEditText>(R.id.etMail)
        val etPassword=findViewById<TextInputEditText>(R.id.etPassword)
        val etUniqueId=findViewById<TextInputEditText>(R.id.etUniqueId)


        binding.btnSignUp.setOnClickListener {
            val name=etName.text.toString()
            val mail=etMail.text.toString()
            val unqiueId=etUniqueId.text.toString()
            val password=etPassword.text.toString()

         val user=User(name,mail,password,unqiueId)
         database= FirebaseDatabase.getInstance().getReference("Users")
         database.child(unqiueId).setValue(user).addOnSuccessListener {
            etName.text?.clear()
             etMail.text?.clear()
             etUniqueId.text?.clear()
             etPassword.text?.clear()

             Toast.makeText(this,"Your Registered in our Databse",Toast.LENGTH_SHORT).show()
         }.addOnFailureListener {
             Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
         }

        }
        binding.tVSignIN.setOnClickListener {
            val intent=Intent(this,SignIn::class.java)
            startActivity(intent)
        }
    }
}