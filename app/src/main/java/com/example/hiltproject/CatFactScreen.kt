package com.example.hiltproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.hiltproject.Utils.FontUtils
import com.example.hiltproject.ViewModel.MainViewModel
import com.example.hiltproject.ui.theme.isDarkMode



@Composable
fun CatFactScreen(modifier: Modifier, viewModel: MainViewModel) {
    var catFactState = viewModel.catFactMLD.observeAsState().value
    var rememberFontSize by remember { mutableStateOf(64.sp) }
    var rememberLineHeight by remember { mutableStateOf(70.sp) }

    Surface(modifier = modifier
        .fillMaxSize()
        .background(
            color =
            if (isDarkMode())
                Color(0xFF31505c)
            else
                Color(0xFF93c7db)
        ),
    ) {
        catFactState?.let {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${catFactState.fact}",
                    modifier = Modifier.padding(12.dp),
                    style = TextStyle(
                        fontFamily = FontUtils.sourceSerif,
                        fontSize = rememberFontSize,
                        color = catFactState.color
                    ),
                    lineHeight = rememberLineHeight,
                    onTextLayout = { textLayoutResult ->
                        if (textLayoutResult.hasVisualOverflow) {
                            rememberFontSize = 0.9f * rememberFontSize
                            rememberLineHeight = 0.9f * rememberLineHeight
                        }
                    }
                )
            }
        }
    }
}