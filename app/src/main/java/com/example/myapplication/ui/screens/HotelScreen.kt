@file:OptIn(ExperimentalFoundationApi::class)

package com.example.myapplication.ui.screens

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.network.models.HotelModel
import com.example.myapplication.ui.custom_composables.DescriptionCard
import com.example.myapplication.ui.custom_composables.PhotoCarousel
import com.example.myapplication.ui.theme.ButtonBlue
import com.example.myapplication.ui.theme.CardTextYellow
import com.example.myapplication.ui.theme.CardYellow
import com.example.myapplication.ui.theme.CommonBackgroundColor
import com.example.myapplication.ui.theme.DescriptionCardColor
import com.example.myapplication.ui.theme.PriceForItColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HotelScreen(navController: NavController, hotel: HotelModel) {
    Scaffold(
        modifier = Modifier.background(CommonBackgroundColor),
        topBar = { TopBar() },
        bottomBar = { BottomBar(navController) },
        containerColor = CommonBackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(
                    topStart = 0.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 12.dp
                )
            ) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                PhotoCarousel(hotel.imageUrls)
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 16.dp
                    ),
                ) {
                    Spacer(modifier = Modifier.padding(6.dp))
                    RatingCard(hotel = hotel)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = hotel.name ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    ClickableText(
                        text = AnnotatedString(hotel.adress ?: ""), onClick = {}, style = TextStyle(
                            fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue
                        )
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Bottom),
                            text = "${hotel.minimalPrice.toString()} ₽",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Column(verticalArrangement = Arrangement.Bottom) {
                            Text(
                                text = hotel.priceForIt ?: "",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = PriceForItColor
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.about_hotel),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        for (i in 0 until (hotel.aboutTheHotel?.peculiarities?.size ?: 0)) {
                            DescriptionCard(
                                description = hotel.aboutTheHotel?.peculiarities?.get(i) ?: ""
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = hotel.aboutTheHotel?.description ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(containerColor = CommonBackgroundColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.emoji_happy),
                                    contentDescription = "Comfy",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Удобства",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Самое необходимое",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = PriceForItColor
                                    )
                                }
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow),
                                contentDescription = "Arrow",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.tick_square),
                                    contentDescription = "Comfy",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Что включено",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Самое необходимое",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = PriceForItColor
                                    )
                                }
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow),
                                contentDescription = "Arrow",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_square),
                                    contentDescription = "Comfy",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Что не включено",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "Самое необходимое",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = PriceForItColor
                                    )
                                }
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow),
                                contentDescription = "Arrow",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.hotel),
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                navController.navigate("rooms_screen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text(
                text = stringResource(id = R.string.to_room_choice),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun RatingCard(hotel: HotelModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(containerColor = CardYellow),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                modifier = Modifier.padding(
                    top = 3.dp, bottom = 3.dp, start = 3.dp, end = 7.dp
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "star",
                    tint = CardTextYellow
                )
                Text(
                    text = "${hotel.rating ?: ""} ${hotel.ratingName ?: ""}",
                    fontSize = 16.sp,
                    color = CardTextYellow,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


