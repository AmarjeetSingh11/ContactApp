package com.example.contactmanagerapp

import android.app.Dialog
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactmanagerapp.databinding.ActivityMainScreenAppBinding
import com.example.contactmanagerapp.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainScreenApp : AppCompatActivity() {

    lateinit var binding: ActivityMainScreenAppBinding

    lateinit var dialog: Dialog

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding= ActivityMainScreenAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addButton=findViewById<Button>(R.id.btnAdd)
        val etname=findViewById<TextInputEditText>(R.id.etname)
        val etnumber=findViewById<TextInputEditText>(R.id.etnumber)


        dialog=Dialog(this)
        dialog.setContentView(R.layout.customiseddialog)
        binding.btnAdd.setOnClickListener {
            val name=etname.text.toString()
            val number=etnumber.text.toString()

            val contact=Contact(name,number)
            database=FirebaseDatabase.getInstance().getReference("Users/Contact")
            database.child(number).setValue(contact).addOnSuccessListener {

                etname.text?.clear()
                etnumber.text?.clear()

                dialog.show()

            }.addOnFailureListener {
                Toast.makeText(this,"Sorry",Toast.LENGTH_SHORT).show()
            }
            var buttonOk=dialog.findViewById<Button>(R.id.btnOk)
            buttonOk.setOnClickListener {
                dialog.dismiss()
            }
        }

    }
}