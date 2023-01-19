package com.example.ecvadmin.fragments

import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.ecvadmin.R
import com.example.ecvadmin.adapter.ProductAdapter
import com.example.ecvadmin.databinding.FragmentProductBinding
import com.example.ecvadmin.model.ProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater)


        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)

        getData()

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_productFragment_to_addProductFragment)

        }
        return binding.root
    }

    private fun getData() {
        val list = ArrayList<ProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(ProductModel::class.java)
                    list.add(data!!)
                }
                binding.productRecycler.adapter = ProductAdapter(requireContext(), list)
            }
    }
}