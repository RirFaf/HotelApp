package com.example.myapplication.ui.custom_composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun DotsIndicator(
    totalDots: Int, selectedIndex: Int = 0
) {
    Card(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .padding(7.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(totalDots) { index ->
                if (index == selectedIndex) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(color = Color.DarkGray)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(color = Color.LightGray)
                    )
                }
                if (index != totalDots - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoCarousel(
    imageUrls: ArrayList<String>
) {
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0, initialPageOffsetFraction = 0f
        ) {
            imageUrls.size
        }
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = true,
            reverseLayout = false,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = { imageUrls[it] },
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                Orientation.Horizontal
            ),
        ) { index ->
            Card(
                modifier = Modifier.padding(horizontal = 16.dp), shape = RoundedCornerShape(16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(imageUrls[index]).crossfade(true).build(),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column {
            DotsIndicator(totalDots = imageUrls.size, selectedIndex = pagerState.currentPage)
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
        }
    }
}
