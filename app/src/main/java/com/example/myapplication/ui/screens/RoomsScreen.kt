package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.RoomModel
import com.example.myapplication.network.models.HotelModel
import com.example.myapplication.ui.custom_composables.DescriptionCard
import com.example.myapplication.ui.custom_composables.PhotoCarousel
import com.example.myapplication.ui.theme.ButtonBlue
import com.example.myapplication.ui.theme.CommonBackgroundColor
import com.example.myapplication.ui.theme.PaleButtonBlue
import com.example.myapplication.ui.theme.PriceForItColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomsScreen(navController: NavController, rooms: ArrayList<RoomModel>, hotelName: String) {
    Scaffold(
        modifier = Modifier.background(CommonBackgroundColor),
        topBar = { TopBar(navController = navController, hotelName = hotelName) },
        bottomBar = {},
        containerColor = CommonBackgroundColor
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            itemsIndexed(rooms) { _, room ->
                RoomCard(room = room, navController = navController)
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController, hotelName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "left arrow",
                tint = Color.Black
            )
        }
        Text(
            text = hotelName,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 18.sp
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RoomCard(room: RoomModel, navController:NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        PhotoCarousel(imageUrls = room.imageUrls)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 16.dp
                )
        ) {
            Text(
                text = room.name ?: "",
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = Color.Black
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                for (i in 0 until room.peculiarities.size) {
                    DescriptionCard(description = room.peculiarities[i])
                }
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = 4.dp, bottom = 4.dp)
                    .clickable { /*TODO*/ },
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(containerColor = PaleButtonBlue)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                ) {
                    Text(
                        text = stringResource(R.string.more_about_room),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = ButtonBlue
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "Right",
                        tint = ButtonBlue
                    )
                }
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = "${room.price} ₽",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Column(verticalArrangement = Arrangement.Bottom) {
                    Text(
                        text = room.pricePer ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = PriceForItColor
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                }
            }
            Button(
                onClick = {
                    navController.navigate("booking_screen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
            ) {
                Text(
                    text = stringResource(id = R.string.choose_this_room),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
