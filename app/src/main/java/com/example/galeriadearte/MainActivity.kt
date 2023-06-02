package com.example.galeriadearte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galeriadearte.ui.theme.GaleriaDeArteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriaDeArteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryScreen()
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen() {
    var artShow by remember {
        mutableStateOf(0)
    }
    var artShown = painterResource(id = R.drawable.img_1)
    var artTitle = stringResource(id = R.string.img_1)
    var artAuthor = stringResource(id = R.string.author_img_1)
    when (artShow) {
        0 -> {
            artShown = painterResource(id = R.drawable.img_1)
            artTitle = stringResource(id = R.string.img_1)
            artAuthor = stringResource(id = R.string.author_img_1)
        }
        1 -> {
            artShown = painterResource(id = R.drawable.img_2)
            artTitle = stringResource(id = R.string.img_2)
            artAuthor = stringResource(id = R.string.author_img_2)
        }
        2 -> {
            artShown = painterResource(id = R.drawable.img_3)
            artTitle = stringResource(id = R.string.img_3)
            artAuthor = stringResource(id = R.string.author_img_3)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box (Modifier.wrapContentHeight()) {
            Image(
                painter = artShown,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(500.dp)

            )
        }

        ArtGalleryInfo(artTitle, artAuthor)
        ArtGalleryControl(
            artShowNext = { if (artShow < 2 ) artShow++ else artShow = 0},
            artShowPrevious = { if (artShow > 0 ) artShow-- else artShow = 2 })
    }
}

@Composable
fun ArtGalleryInfo(
    artTitle: String,
    artAuthor: String
) {
    Column(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Text(
            text = artTitle,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = artAuthor,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ArtGalleryControl(
    artShowNext: () -> Unit,
    artShowPrevious: () -> Unit
    ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
       Button(onClick = artShowPrevious,
           modifier = Modifier
               .height(40.dp)
               .width(125.dp),
           shape = RoundedCornerShape(5.dp)
       ) {
           Text(text = stringResource(id = R.string.previous_btn))
       }
       Button(onClick = artShowNext,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .height(40.dp)
                .width(125.dp),
           shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = stringResource(id = R.string.next_btn))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    GaleriaDeArteTheme {
        ArtGalleryScreen()
    }
}