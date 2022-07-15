package com.nikhil.composeui.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikhil.composeui.ui.main.view.Movie
import com.nikhil.composeui.ui.main.view.MovieItem
import com.nikhil.composeui.ui.theme.ComposeUiTheme
import com.nikhil.composeui.ui.theme.Typography
import com.nikhil.composeui.ui.theme.quickSand
import com.nikhil.composeui.utils.randomColor

class MainActivity : ComponentActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyContentView {
                MovieList(viewModel) {
                    //  Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
                    viewModel.title.value = it.name
                    viewModel.randomColor.value = randomColor()

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = MainViewModel()
    MyContentView {
        MovieList(viewModel) {

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
fun MovieList(viewModel: MainViewModel, selectedMovie: (Movie) -> Unit) {
    val listState = rememberLazyListState()

    val targetedColor: Color by animateColorAsState(
        targetValue = viewModel.randomColor.value,
        animationSpec = tween(durationMillis = 400)
    )
    Column(verticalArrangement = Arrangement.Center) {
        Text(
            text = "Selected : " + viewModel.title.value,
            textAlign = TextAlign.Center,
            color = targetedColor,
            fontSize = 20.sp,
            style= MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(16.dp), state = listState
        ) {
            items(items = viewModel.movieList) { item: Movie ->
                Box(modifier = Modifier.clickable { selectedMovie(item) }) {
                    MovieItem(movie = item)
                }
                Divider()

            }

        }
    }


}
