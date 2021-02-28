package com.example.androiddevchallenge.ui

abstract class Models(
    var isLoading: Boolean = true
)

data class PetDetailsModel(
    val pets: List<String> = arrayListOf(),
    var pet: String = "Start"
) : Models()
