package com.example.myapplication23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.GONE
        show.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)


        }
        save.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            var name = PersonName.text.toString()
            var number = PersonNumber.text.toString()
            var address = PersonAddress.text.toString()


            if(name.isEmpty() || number.isEmpty() || address.isEmpty() ){
                progressBar2.visibility = View.GONE
                Toast.makeText(this, "please add data in field", Toast.LENGTH_SHORT).show()


            }else{

                val person = hashMapOf(
                    "name" to "$name",
                    "number" to "$number",
                    "address" to "$address"

                )
                db.collection("person").add(person).addOnSuccessListener {e ->
                    progressBar2.visibility = View.GONE

                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()

                    this.PersonName.text.clear()
                    this.PersonNumber.text.clear()
                    this.PersonAddress.text.clear()

                }.addOnFailureListener {
                        e->
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                }
            }

        }


    }
}