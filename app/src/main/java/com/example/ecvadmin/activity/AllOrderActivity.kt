package com.example.ecvadmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.ecvadmin.R
import com.example.ecvadmin.adapter.AllOrderAdapter
import com.example.ecvadmin.databinding.ActivityAllOrderBinding
import com.example.ecvadmin.model.AllOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllOrderBinding

    private lateinit var list: ArrayList<AllOrderModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        Firebase.firestore.collection("allOrders").get().addOnSuccessListener {
            list.clear()
            for(doc in it){
                val data = doc.toObject(AllOrderModel::class.java)
                list.add(data)
            }
            recyclerView.adapter = AllOrderAdapter(list, this)
        }

    }
}