package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ButtonBlue

@Composable
fun ErrorScreen(retryAction: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.loading_failed))
        Button(
            onClick = retryAction,
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}