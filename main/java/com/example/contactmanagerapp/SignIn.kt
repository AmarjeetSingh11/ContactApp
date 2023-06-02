package com.example.contactmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var databaseRefernece:DatabaseReference

    companion object{
        const val KEY1="com.example.contactmanagerapp.SignIn.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()
        val signInButton=findViewById<Button>(R.id.btnSignIn)
        val username=findViewById<TextInputEditText>(R.id.etUsername)


        signInButton.setOnClickListener {
            val uniqueId=username.text.toString()
            if(uniqueId.isNotEmpty()){   //isma mai check kar rha hu uniqueId not empty hai toh usko laka ajo
                readData(uniqueId)
            }else{
                //agar empty hai userId toh ek message print karo ki bhaiya registered karo

                Toast.makeText(this,"Pleas enter the User Name ",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun readData(uniqueId:String){
        databaseRefernece=FirebaseDatabase.getInstance().getReference("Users")
        databaseRefernece.child(uniqueId).get().addOnSuccessListener {

            if(it.exists()){


                val id=it.child("uniqueName").value

                
                val intent=Intent(this,MainScreenApp::class.java)

                startActivity(intent)

            }else{
                Toast.makeText(this,"This Unique Id is Invaild",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Server Failed",Toast.LENGTH_SHORT).show()
        }
    }
}