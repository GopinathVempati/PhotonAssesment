package com.example.photonassesment.ui.feature.school_details


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.photonassesment.ui.feature.schools.SchoolDetails
import com.example.photonassesment.model.NySchoolItem
import kotlin.math.min


@Composable
fun SchoolDetailsScreen(state: SchoolDetailsContract.State) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Surface(elevation = 4.dp) {
                SchoolDetailsCollapseToolbar(state.nySchoolItem, scrollOffset)
            }
            Spacer(modifier = Modifier.height(2.dp))
            LazyColumn(
                state = scrollState, contentPadding = PaddingValues(bottom = 16.dp)
            ) {}
        }
    }
}

@Composable
private fun SchoolDetailsCollapseToolbar(
    nySchoolItem: NySchoolItem?,
    scrollOffset: Float,
) {
    Row {
        Card(
            modifier = Modifier.padding(16.dp), shape = CircleShape, border = BorderStroke(
                width = 2.dp, color = Color.Black
            ), elevation = 4.dp
        ) {}
        SchoolDetails(
            schoolItem = nySchoolItem,
            expandedLines = (kotlin.math.max(3f, scrollOffset * 6)).toInt(),
            modifier = Modifier
                .padding(
                    end = 16.dp, top = 16.dp, bottom = 16.dp
                )
                .fillMaxWidth()
        )
    }
}