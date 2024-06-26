package com.infinitelearning.tugasadvance.presentation.screen.map

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.infinitelearning.tugasadvance.R


@Composable
fun MapScreen (
    navController: NavController,
    modifier: Modifier = Modifier
) {
    MapsContent(navController = navController, modifier = modifier)
}

@Composable
fun MapsContent(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                isMyLocationEnabled = false
            )
        )
    }
    var selectedMapTypeOption by remember { mutableStateOf(MapViewModel.NORMAL) }

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            val context = LocalContext.current
            val tugasAdvanceApp = LatLng(-7.783214752320985, 110.38682963669578)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(tugasAdvanceApp, 12f)
            }
            var properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
            var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            ){
                Marker(
                    state = MarkerState(tugasAdvanceApp),
                    title = "Biskop Di Jogja"
                )
            }
        }
    }
}