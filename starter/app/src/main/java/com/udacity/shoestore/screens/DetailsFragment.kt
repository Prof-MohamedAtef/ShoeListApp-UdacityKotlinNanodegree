package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.SharedViewModel


class DetailsFragment:Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val viewModel:SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false)
        binding.viewmodel=viewModel
        binding.myShoe=Shoe()

        binding.btnAddShoe.setOnClickListener { view: View ->
            if(validateData()){
                saveShoeDetails()
            }
        }

        binding.btnCancel.setOnClickListener { view:View->
            findNavController().navigateUp()
        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { changedList->
            if (changedList==true){
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
                viewModel.doneNavigatingToDetail()
            }
        })
        return binding.root
    }

    private fun saveShoeDetails() {
        val name=binding.etShoeName.text.toString()
        val size= binding.etShoeSize.text.toString()
        val company=binding.etShoeDesc.text.toString()
        val description=binding.etShoeCompany.text.toString()
        viewModel.onSaveShoeData(name,company, size.toDouble() ,description)
    }

    private fun validateData(): Boolean {
        if (binding.etShoeName.text.isNullOrEmpty()) {
            binding.etShoeName.setError("Enter Shoe Name")
            return false
        }
        if (binding.etShoeSize.text.isNullOrEmpty()) {
            binding.etShoeCompany.setError("Enter Shoe Size")
            return false
        }
        if (binding.etShoeDesc.text.isNullOrEmpty()) {
            binding.etShoeDesc.setError("Enter Shoe Description")

            return false
        }
        if (binding.etShoeCompany.text.isNullOrEmpty()) {
            binding.etShoeCompany.setError("Enter Shoe Company")
            return false
        }
        return true
    }
}