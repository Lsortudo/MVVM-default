package com.example.mvvmdefault.ViewModel.Main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdefault.Models.Photo
import com.example.mvvmdefault.Repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val photoList = MutableLiveData<List<Photo>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPhotos() {

        val request = repository.getAllPhotos()
        request.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                photoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                errorMessage.postValue(t.message)
                errorMessage
            }

        })
    }
}