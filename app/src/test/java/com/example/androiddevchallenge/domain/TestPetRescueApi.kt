package com.example.androiddevchallenge.domain

import com.example.androiddevchallenge.CoroutineRule
import com.example.androiddevchallenge.ui.viewmodels.PetDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TestPetRescueApi {

    @Rule
    @JvmField
    var coroutineRule = CoroutineRule()

    @Test
    fun testGetPets(){
        val vm = PetDetailsViewModel()
        vm.getAToken()
    }
}