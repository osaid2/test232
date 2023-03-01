package com.example.myapplication23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create a new user with a first and last name
save.setOnClickListener {
    var name = PersonName.text.toString()
    var age = PersonAge.text.toString()
var id = PersonID.text.toString()
    val user = hashMapOf(
        "name" to "$name",
        "id" to "$id",
        "age" to "$age"
    )
    // Add a new document with a generated ID
    db.collection("users")
        .add(user)
        .addOnSuccessListener { e ->
            Toast.makeText(this, "susses", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener { e ->
            Toast.makeText(this, "faield", Toast.LENGTH_SHORT).show()
        }

}
//read firebase firestore
//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
        //add the data in array making
//                }
//            }
//            .addOnFailureListener { exception ->
//            }

    }
}