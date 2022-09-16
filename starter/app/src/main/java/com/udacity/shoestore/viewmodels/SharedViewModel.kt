package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class SharedViewModel:ViewModel() {

    var _shoeList  =  MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    val _navigateToDetail = MutableLiveData<Boolean>()
    val navigateToDetail: LiveData<Boolean?>
        get() = _navigateToDetail

    init {
        _shoeList.value = ArrayList()
    }

    fun doneNavigatingToDetail() {
        _navigateToDetail.value = false
    }

    fun onClickToDetail() {
        _navigateToDetail.value = true
    }

    fun onSaveShoeData(name: String, company: String, size:Double, description: String){
        val newItem =Shoe(name , size ,company,description)
        _shoeList.value?.add(newItem)
        onClickToDetail()
    }
}