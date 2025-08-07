package com.example.laboratorio4

import androidx.annotation.DrawableRes

data class Mascota(
    val id: Int,
    val nombre: String,
    val raza: String,
    @DrawableRes val foto: Int,
    var adoptado: Boolean = false
)