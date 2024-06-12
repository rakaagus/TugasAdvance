package com.infinitelearning.tugasadvance.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.infinitelearning.tugasadvance.domain.model.FavData
import com.infinitelearning.tugasadvance.domain.model.Movie
import com.infinitelearning.tugasadvance.presentation.screen.fav.FavViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val movies by viewModel.movies.collectAsState()


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie,
            )
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    viewModel: FavViewModel = hiltViewModel(),
) {
    val isFavorite = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.aspectRatio(2f / 3)) {
            // Menampilkan gambar poster
            movie.posterPath?.let { posterPath ->
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = "https://image.tmdb.org/t/p/w500$posterPath")
                            .apply {
                                crossfade(true)
                            }
                            .build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(2f / 3)
                )
            }
            // Menampilkan icon favorite di pojok kanan atas
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = if (isFavorite.value) Color.Red else Color.White,
                modifier = Modifier
                    .clickable {
                        isFavorite.value = !isFavorite.value
                        //add and delete fav
                        val fav = FavData(movie.id, movie.title, movie.posterPath)
                        if (isFavorite.value){
                            viewModel.addFav(fav)
                        } else{
                            viewModel.deleteFav(fav)
                        }
                    }
                    .size(64.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
            )
        }

        // Menampilkan judul film
        Text(
            text = movie.title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        // Menampilkan deskripsi film
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}