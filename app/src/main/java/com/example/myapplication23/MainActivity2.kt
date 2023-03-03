package com.example.myapplication23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        delete.setOnClickListener {
            var results = false;
            progressBar2.visibility = View.VISIBLE
            db.collection("person")
                .get()
                .addOnSuccessListener { result ->

                    for (document in result) {
                        db.collection("person").document(document.id).delete().addOnSuccessListener { e->
                        }
                        getData()
                        progressBar2.visibility = View.GONE
                    }
                }
        }
        getData()
    }
    fun getData() {

        db.collection("person")
            .get()
            .addOnSuccessListener { result ->
                progressBar2.visibility = View.GONE
                val arrayAdapter: ArrayAdapter<*>
                val users = ArrayList<String>()
                for (document in result) {
                    var name = document.get("name").toString()
                    var number = document.get("number").toString()
                    var address = document.get("address").toString()
                    val result =
                        "The Pesron Data is\n id :${document.id} \n the name:$name \n number:$number \n address:$address \n"
                    users.add(result)
                }
                var mListView = findViewById<ListView>(R.id.mylist)
                arrayAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1, users
                )
                mListView.adapter = arrayAdapter
            }
            .addOnFailureListener { exception ->
            }

    }
}