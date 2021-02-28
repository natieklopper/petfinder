package com.example.androiddevchallenge.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.PetApiAdapter
import com.example.androiddevchallenge.ui.PetDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetDetailsViewModel : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val petDetails = PetDetailsModel()
    val model = MutableLiveData<PetDetailsModel>()

    fun getAToken() {
        petDetails.pet = "Loading!"
        model.postValue(petDetails)

        launch {
            try {
                val response =
                    PetApiAdapter.apiClient.getAnimals(
                        ""
                    )

                val data = response.body()
                print(data.toString())

                updateModel(PetDetailsModel(pet = "Done!"))

            } catch (e: Exception) {
                print(e.toString())
            }
        }
    }

    private fun updateModel(model: PetDetailsModel) {
        val tmp = petDetails.copy(
            pet = model.pet,
            pets = model.pets,
        )
        tmp.isLoading = false
        this.model.postValue(tmp)
    }
}