package com.example.mvvmdefault

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdefault.Repositories.MainRepository
import com.example.mvvmdefault.Services.RetrofitService
import com.example.mvvmdefault.ViewModel.Main.MainViewModel
import com.example.mvvmdefault.ViewModel.Main.MainViewModelFactory
import com.example.mvvmdefault.adapters.MainAdapter
import com.example.mvvmdefault.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
// Inicializações/Instancias

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerview.adapter = adapter


    }

    override fun onStart() {
        super.onStart()

        viewModel.photoList.observe(this, Observer {
            Log.i("mvvmLifeCycle", "onStart")
            adapter.setPhotoList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllPhotos()
    }
}