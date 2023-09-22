package com.example.myapplication.ui.custom_composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.DescriptionCardColor
import com.example.myapplication.ui.theme.PriceForItColor


@Composable
fun DescriptionCard(description: String) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = DescriptionCardColor)
    ) {
        Text(
            text = description,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
            color = PriceForItColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}