package com.example.mvvmdefault.Models

data class Photo(
    var id: Int,
    var author: String,
    var width: Int,
    var height: Int,
    var goToUrl: String,
    var thumbnailUrl: String,
)
