package com.infinitelearning.tugasadvance.presentation.screen.fav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.infinitelearning.tugasadvance.domain.model.FavData
import com.infinitelearning.tugasadvance.domain.repository.AllFavList
import com.infinitelearning.tugasadvance.presentation.navigation.Screen

@Composable
fun FavScreen(
   viewModel: FavViewModel = hiltViewModel(), 
) {
    
    val favList by viewModel.favList.collectAsState(initial = emptyList())
    
    Column(
        modifier = Modifier.padding(12.dp)
    ){
        FavContent(favList = favList)
    }
}

@Composable
fun FavContent(
    favList: AllFavList,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
    ) {
        items(
            items = favList
        ) {fav ->
            FavCard(favData = fav)
            
        }
    }
}

@Composable
fun FavCard(
    favData: FavData,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "https://image.tmdb.org/t/p/w500${favData.image}")
                        .apply {
                            crossfade(true)
                        }
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = favData.title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

