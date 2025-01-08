package com.example.googlemaps

import GoogleMapView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.material3.Scaffold


import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.googlemaps.ui.theme.GOOGLEMAPSTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.maps.android.compose.rememberCameraPositionState


val indiaState= LatLng(20.593684,78.996288)
 val defaultCameraPosition = CameraPosition.fromLatLngZoom(indiaState,4f)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<SearchViewModel>()


        if (!Places.isInitialized()){
            Places.initialize(applicationContext,"AIzaSyBgFHyAGoP20poBbDk_BuUKhikG02bplOo")
        }





      //  enableEdgeToEdge()
        setContent {
            GOOGLEMAPSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var cameraPositionState = rememberCameraPositionState{
                        position=defaultCameraPosition
                    }


                    GoogleMapView(
                        viewModel,
                        modifier = Modifier.height(350.dp),
                        cameraPositionState = cameraPositionState,
                        onMapLoaded = {

                        }
                    )
                }
            }
        }
    }
}
