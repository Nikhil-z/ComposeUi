package com.nikhil.composeui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhil.composeui.ui.items.Movie
import com.nikhil.composeui.ui.items.MovieItem
import com.nikhil.composeui.ui.theme.ComposeUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyContentView {
                val movies = getDummyMovieData()
                MovieList(movies) {
                    Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyContentView {
        val movies = getDummyMovieData()
        MovieList(movies) {

        }
    }
}


@Composable
fun MyContentView(content: @Composable () -> Unit) {
    ComposeUiTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White //MaterialTheme.colors.background
        ) {
            content()
        }
    }

}


@Composable
fun MovieList(movieList: ArrayList<Movie> = arrayListOf(), selectedMovie: (Movie) -> Unit) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .padding(16.dp), state = listState
    ) {
        items(items = movieList) { item: Movie ->
            Box(modifier = Modifier.clickable { selectedMovie(item) }) {
                MovieItem(movie = item)
            }
            Divider()
        }
    }
}


private fun getDummyMovieData(): ArrayList<Movie> {

    return List(100) {
        Movie(
            name = "$it Hello Android", desc = "${System.currentTimeMillis()}", category = "Horror",
            imageUrl = "https://picsum.photos/${200 + it}"
        )
    } as ArrayList<Movie>
}