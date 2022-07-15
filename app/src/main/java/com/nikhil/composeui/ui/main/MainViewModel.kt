package com.nikhil.composeui.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.nikhil.composeui.ui.main.view.Movie


/**
 * @Author: Nikhil
 * @Date: 15,July,2022
 */
class MainViewModel : ViewModel() {

    var title = mutableStateOf("None")
    var movieList: ArrayList<Movie> = ArrayList()
    var randomColor = mutableStateOf(Color.Black)


    init {
        movieList = getDummyMovieData()
    }

    private fun getDummyMovieData(): ArrayList<Movie> {

        return List(100) {
            Movie(
                name = "$it Hello Android",
                desc = "${System.currentTimeMillis()}",
                category = "Horror",
                imageUrl = "https://picsum.photos/${200 + it}"
            )
        } as ArrayList<Movie>
    }
}