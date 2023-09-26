package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.SelectedRoomModel
import com.example.myapplication.ui.theme.ButtonBlue
import com.example.myapplication.ui.theme.CommonBackgroundColor
import com.example.myapplication.ui.theme.LabelColor
import com.example.myapplication.ui.theme.PaleButtonBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController, selectedRoomModel: SelectedRoomModel) {
    var guestsNum by remember { mutableIntStateOf(1) }
    Scaffold(
        modifier = Modifier.background(CommonBackgroundColor),
        topBar = { TopBar(navController = navController) },
        bottomBar = {},
        containerColor = CommonBackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    RatingCard(
                        rating = selectedRoomModel.horating.toString(),
                        ratingName = selectedRoomModel.ratingName
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = selectedRoomModel.hotelName ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    ClickableText(
                        text = AnnotatedString(selectedRoomModel.hotelAdress ?: ""),
                        onClick = {},
                        style = TextStyle(
                            fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = ButtonBlue
                        )
                    )
                }
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    CustomTextRow(
                        fieldName = "Вылет из",
                        fieldValue = selectedRoomModel.departure
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    CustomTextRow(
                        fieldName = "Страна, город",
                        fieldValue = selectedRoomModel.arrivalCountry
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    CustomTextRow(
                        fieldName = "Даты",
                        fieldValue = "${selectedRoomModel.tourDateStart}-${selectedRoomModel.tourDateStop}"
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    CustomTextRow(
                        fieldName = "Кол-во ночей",
                        fieldValue = selectedRoomModel.departure
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    CustomTextRow(
                        fieldName = "Отель",
                        fieldValue = selectedRoomModel.hotelName
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    CustomTextRow(
                        fieldName = "Номер",
                        fieldValue = selectedRoomModel.room
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    CustomTextRow(
                        fieldName = "Питание",
                        fieldValue = selectedRoomModel.nutrition
                    )
                }
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Информация о покупателе",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 22.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Эти данные никому не передаются. После оплаты мы вышли чек на указанный вами номер и почту",
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }
            }
            LazyColumn(){
                items(guestsNum){index ->  
                    InfoCard(guestNum = index)
                }
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
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
            text = stringResource(id = R.string.booking),
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 18.sp
        )
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "left arrow",
                tint = Color.Transparent
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfoCard(guestNum: Int) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$guestNum-й гость",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 22.sp
                )
                Row(
                    modifier = Modifier
                        .clickable { expanded = !expanded },
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(PaleButtonBlue)
                    ) {
                        Icon(
                            modifier = Modifier.padding(
                                start = 3.dp,
                                end = 3.dp,
                                top = 2.dp,
                                bottom = 2.dp
                            ),
                            painter = painterResource(id = if (expanded) R.drawable.up_arrow else R.drawable.down_arrow),
                            contentDescription = "Expand icon",
                            tint = ButtonBlue
                        )
                    }
                }
            }
            if (expanded) {
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        label = { Text(text = "") }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomTextField(
    value: String,
    onValueChange: () -> Unit,
    label: @Composable() (() -> Unit)
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange() },
        modifier = Modifier.fillMaxWidth(),
        label = label,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = CommonBackgroundColor,
            cursorColor = Color.Black,
            focusedLabelColor = LabelColor,
            unfocusedLabelColor = LabelColor,
            focusedIndicatorColor = CommonBackgroundColor,
            unfocusedIndicatorColor = CommonBackgroundColor
        )
    )
}

@Composable
private fun CustomTextRow(fieldName: String, fieldValue: String?) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = fieldName,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = fieldValue ?: "",
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}