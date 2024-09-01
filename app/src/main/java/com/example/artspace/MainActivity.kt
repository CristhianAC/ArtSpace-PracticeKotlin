package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainContainer()
                }
            }
        }
    }
}

data class Art(
    val title: String,
    @DrawableRes val image: Int,
    val textArtist: String
)

val arts = listOf(
    Art("Obra 1", R.drawable.pexels_nastipa_10083610, "Artista 1"),
    Art("Obra 2", R.drawable.man_silhouette_person_sunset_camera_photography_768707_pxhere_com, "Artista 2"),

)

@Composable
fun MainContainer(modifier: Modifier = Modifier) {
    var currentArtIndex by remember { mutableIntStateOf(0) }

    val currentArt = arts[currentArtIndex]

    Column(
        modifier = modifier.padding(horizontal = 18.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ImageArt(painterResource = currentArt.image)
        ContentDescription(modifier, currentArt.title, currentArt.textArtist)
        ButtonsSections(
            onPreviousClick = {
                currentArtIndex = if (currentArtIndex - 1 < 0) arts.size - 1 else currentArtIndex - 1
            },
            onNextClick = {
                currentArtIndex = if (currentArtIndex + 1 >= arts.size) 0 else currentArtIndex + 1
            },
            modifier = modifier
        )
    }
}

@Composable
fun ContentDescription(modifier: Modifier = Modifier, textTitle: String, textArtist: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = textTitle,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Text(
            text = textArtist,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = modifier
        )
    }
}

@Composable
fun ButtonsSections(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(onClick = onPreviousClick) {
            Text(text = "Previous")
        }
        Button(onClick = onNextClick) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ImageArt(modifier: Modifier = Modifier, @DrawableRes painterResource: Int) {
    Box(
        modifier = modifier
            .padding(top = 4.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .background(color = Color(0xFFD9D9F3))
            .padding(12.dp)
            .shadow(
                elevation = 24.dp, // Set the elevation for the shadow
                shape = RoundedCornerShape(8.dp), // Optional: define the shape of the shadow
            )
    ) {
        Image(painter = painterResource(id = painterResource), contentDescription = null)
    }
}

@Preview
@Composable
fun MainPreview() {
    MainContainer()
}
