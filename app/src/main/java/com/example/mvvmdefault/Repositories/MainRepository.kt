package com.example.mvvmdefault.Repositories

import com.example.mvvmdefault.Services.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
// nome é main por conta da MainActivity, caso fosse SignUpActivity seria SignUpRepository

    fun getAllPhotos() = retrofitService.getAllPhotos()

// Motivo da criação de um Repository: Caso mais pra frente vá trocar a biblioteca pra requisição web ou mesmo mockar os dados,
// só preciso trocar dependencia dentro da função e trocar a chamada na variavel acima após e não alterarei em mais nada,
// ao invés de percorrer todos arquivos que chamariam o retrofitService.
}