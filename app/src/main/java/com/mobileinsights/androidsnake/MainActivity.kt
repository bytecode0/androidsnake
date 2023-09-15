package com.mobileinsights.androidsnake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
                    SnakeLogo()
                }
            }
        }
    }
}

@Composable
fun SnakeLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.snake_image) , 
        contentDescription = stringResource(id = R.string.snake_image)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidSnakeTheme {
        SnakeLogo()
    }
}