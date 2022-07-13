package com.nikhil.composeui.ui.items

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nikhil.composeui.R

@Composable
fun MovieItem(movie: Movie) {

    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        var isSelected by remember { mutableStateOf(false) }
        val targetedColor: Color by animateColorAsState(
            targetValue = if (isSelected) Color.Black else Color.Gray,
            animationSpec = tween(durationMillis = 400)
        )

        Surface(color = targetedColor) {
            Row(
                Modifier
                    .padding(4.dp)
                  //  .clickable { isSelected = !isSelected }
                    .fillMaxSize()
            ) {


                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = movie.desc,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        // .fillMaxHeight()
                        //.fillMaxWidth()
                        .padding(10.dp)
                        // .wrapContentHeight()
                        .weight(0.3f)
                        .clip(CircleShape)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.7f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(2.dp,5.dp)
                    )
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .background(
                                Color.Red
                            )
                            .padding(2.dp)
                    )
                    Text(
                        text = movie.desc,
                        style = MaterialTheme.typography.subtitle2,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}



data class Movie(val name: String, val category: String, val desc: String, val imageUrl: String)