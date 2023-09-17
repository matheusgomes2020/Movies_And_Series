package com.example.moviesaandseries.presentation.series_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeriesListScreen(
    navController: NavController,
    viewModel: SeriesListViewModel = hiltViewModel()
){

    val statePopular = viewModel.statePopular.value
    val stateOnAir = viewModel.stateOnAir.value
    val stateAiringToday = viewModel.stateAiryngToday.value
    val stateRated = viewModel.stateRated.value

    var querySeries by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf(
            ""
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        statePopular.series?.let { series ->

            Scaffold(

            ) {
                Column {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        query = querySeries,
                        onQueryChange = {
                            querySeries = it
                        },
                        onSearch = {
                            navController.navigate( AppGraph.search_series.SEARCH + "/${querySeries}" ).let {
                                Log.d("GHHHH", "SeriesListScreen: $it")
                            }
                            active = false
                        },
                        active = false,
                        onActiveChange = {
                            active = it
                        },
                        placeholder = {
                            Text(text = "Pesquisar séries...",
                                modifier = Modifier,
                                color = Color.LightGray,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                modifier = Modifier.size(20.dp),
                                tint = Color.LightGray
                            )
                        },
                        trailingIcon = {
                            if ( active ) {
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            if (querySeries.isNotEmpty()) {
                                                querySeries = ""
                                            } else {
                                                active = false
                                            }
                                        }
                                        .size(20.dp),
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Icon",
                                    tint = Color.LightGray
                                )
                            }
                        },
                        shape = RoundedCornerShape(15.dp),
                    ) {

                        Text(text = "Olá")
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(start = 25.dp, end = 25.dp)
                    ) {
                        item {
                            Column {

                                Text(text = "Séries no ar hoje",
                                    modifier = Modifier
                                        .padding(top = 15.dp, bottom = 15.dp),
                                    fontSize = 18.sp,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                //Spacer(modifier = Modifier.height(10.dp))
                                SeriesListScreenCell(navController = navController , state = stateAiringToday)
                                Text(text = "Séries no ar",
                                    modifier = Modifier
                                        .padding(top = 15.dp, bottom = 15.dp),
                                    fontSize = 18.sp,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                //Spacer(modifier = Modifier.height(10.dp))
                                SeriesListScreenCell(navController = navController , state = stateOnAir)
                                Text(text = "Séries populares",
                                    modifier = Modifier
                                        .padding(top = 15.dp, bottom = 15.dp),
                                    fontSize = 18.sp,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                //Spacer(modifier = Modifier.height(10.dp))
                                SeriesListScreenCell(navController = navController , state = statePopular)
                                Text(text = "Séries melhores avaliados",
                                    modifier = Modifier
                                        .padding(top = 15.dp, bottom = 15.dp),
                                    fontSize = 18.sp,
                                    style = MaterialTheme.typography.displayMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                SeriesListScreenCell(navController = navController , state = stateRated)
                                Spacer(modifier = Modifier.height(55.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}



