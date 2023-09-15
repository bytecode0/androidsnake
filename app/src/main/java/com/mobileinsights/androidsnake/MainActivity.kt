package com.mobileinsights.androidsnake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobileinsights.androidsnake.ui.theme.AndroidSnakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSnakeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GradientBackground()
                    GoldenSnake()
                }
            }
        }
    }
}

@Composable
fun GradientBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFffcc00),
                        Color(0xFFff9900)
                    ), // Replace with your gradient colors
                    start = Offset(0f, 0f), // Adjust the start and end points as needed
                    end = Offset(0f, 400.dp.value) // Adjust the end point as needed
                )
            )
    )
}

@Composable
fun Background(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.galaxy01) ,
        contentDescription = stringResource(id = R.string.snake_image),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun GoldenSnake(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.snake_image) ,
        contentDescription = stringResource(id = R.string.snake_image),
        modifier = Modifier.padding(72.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidSnakeTheme {
        Background()
    }
}