/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.skydoves.landscapist.coil.CoilImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "MainList",
        builder = {
            composable("MainList") { MainList(navController = navController) }
            composable("DetailListing") { DetailListing(navController = navController) }
        }
    )
}

@Composable
fun MainList(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "iAdopt", style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Black)) }, modifier = Modifier.height(100.dp)
            )
        }
    ) {

        Column {

            LazyColumn(
                content = {

                    puppyDummy.forEach {
                        item {
                            ItemListing(
                                puppy = it,
                                action = {
                                    print("Clicked")
                                    navController.currentBackStackEntry
                                        ?.arguments?.putParcelable("puppy", it)
                                    navController.navigate("DetailListing")
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ItemListing(puppy: PuppyModel, action: () -> Unit) {

    val imageModifier = Modifier
        .width(180.dp)
        .height(150.dp)
    val cardModifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .padding(horizontal = 16.dp, vertical = 4.dp)
        .clickable(onClick = action)

    val columnModifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
    Card(modifier = cardModifier, elevation = 8.dp) {

        Row() {

            Box(modifier = imageModifier) {
                CoilImage(
                    imageModel = puppy.image,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
                    circularRevealedEnabled = true,
                    // shows a placeholder ImageBitmap when loading.
//              placeHolder = imageResource(R.drawable.placeholder),
//              // shows an error ImageBitmap when the request failed.
//              e
                )
            }
            Column(modifier = columnModifier) {

                Text(puppy.name, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Icon(Icons.Rounded.Place, contentDescription = "Localized description")

                    Text(puppy.location, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.secondary)
                }
            }
        }
    }
}

@Composable
fun DetailListing(navController: NavController) {
    val puppy = navController.previousBackStackEntry
        ?.arguments?.getParcelable<PuppyModel>("puppy")
    val imageModifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(shape = RoundedCornerShape(12.dp))
        .padding(10.dp)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = puppy!!.name, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Black)) }, modifier = Modifier.height(100.dp),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }

            )
        }
    ) {

        Column() {
            Box(modifier = imageModifier) {
                CoilImage(
                    imageModel = puppy!!.image,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
                    circularRevealedEnabled = true,

                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.height(30.dp).width(100.dp)
                        .background(color = Color.Red, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Age: ${puppy!!.age}",
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier.height(30.dp).width(100.dp)
                        .background(color = Color.Magenta, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "${puppy!!.genre}",
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                }
            }

            Row(modifier = Modifier.padding(10.dp)) {
                Icon(Icons.Rounded.Place, contentDescription = "Localized description")

                Text(
                    puppy!!.location,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    puppy!!.breed,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primaryVariant
                )
            }

            Text(
                puppy!!.description,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.padding(10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Phone, contentDescription = "Localized description")
                Text(
                    puppy!!.contactInfo,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    val navController = rememberNavController()
    MyTheme {
        MainList(navController)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    val navController = rememberNavController()
    MyTheme(darkTheme = true) {
        MainList(navController)
    }
}
