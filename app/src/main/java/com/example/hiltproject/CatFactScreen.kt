package com.example.hiltproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiltproject.DataType.CatFactData
import com.example.hiltproject.ui.theme.isDarkMode

@Composable
fun CatFactScreen(modifier: Modifier, viewModel: MainViewModel) {
    var catFactState = viewModel.catFactMLD.observeAsState().value

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                contentPadding = PaddingValues(horizontal = 8.dp),
                content = {
                    items(catFactState) {catFact ->
                        Text(
                            text = "Fact: ${catFact.fact}",
                            modifier = Modifier,
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Divider(color = Color.Gray, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            )
        }
    }
}