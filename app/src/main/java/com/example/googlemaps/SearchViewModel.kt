package com.example.googlemaps

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val placesClient: PlacesClient= Places.createClient(application)
    private val _plaesSuggetions= MutableStateFlow<List<String>>(emptyList())
    val placesSuggetion: StateFlow<List<String>> = _plaesSuggetions.asStateFlow()


    fun fatchPlacesSuggetion(query: String){
        Log.i("SerchData","$query")
        val request= FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener{ response->
                val suggestions =response.autocompletePredictions.map { it.getFullText(null).toString() }
                _plaesSuggetions.value=suggestions

                Log.d("Datay",_plaesSuggetions.toString())

            }

            .addOnFailureListener{e ->
                Log.e("PlacesViewModel","Error fetching place suggestions: ${e.message}")
            }


    }
}