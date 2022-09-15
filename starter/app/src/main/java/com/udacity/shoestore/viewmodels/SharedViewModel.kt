package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class SharedViewModel:ViewModel() {

    private var _shoeList  =  MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private var _user=MutableLiveData<User?>()
    val authUser:LiveData<User?>
        get() = _user

    private val _navigateToList = MutableLiveData<Boolean?>()
    val navigateToList: LiveData<Boolean?>
        get() = _navigateToList

    private val _navigateToDetail = MutableLiveData<Boolean>()

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

    fun onLogOut() {
        _user.value = null
    }

    fun onCreateUser(email: String, password: String) {
        _user.value = User(email, password)
    }


    fun onSaveShoeData(name: String, company: String, size:Double, description: String){
        val newItem =Shoe(name , size ,company,description)
        _shoeList.value?.add(newItem)
        onClickToDetail()
    }
}