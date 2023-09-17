package com.mobileinsights.androidsnake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileinsights.androidsnake.ui.theme.AndroidSnakeTheme
import com.mobileinsights.androidsnake.ui.theme.quirkyRobotFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    AndroidSnakeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GalaxyBackground()
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var isSnakeAnimationFinished by remember { mutableStateOf(false) }

                GoldenSnakeAnimated {
                    isSnakeAnimationFinished = true
                }

                AnimatedVisibility(visible = isSnakeAnimationFinished) {
                    MenuButton(text = "START") {
                        TODO("Implement navigation to game")
                    }
                }
                AnimatedVisibility(visible = isSnakeAnimationFinished) {
                    MenuButton(text = "HIGH SCORES") {
                        TODO("Implement navigation to game")
                    }
                }
                AnimatedVisibility(visible = isSnakeAnimationFinished) {
                    MenuButton(text = "OPTIONS") {
                        TODO("Implement navigation to game")
                    }
                }
             }
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }
    // Define animation specifications
    val infiniteRepeatable by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    // Apply the scale animation to the button
    val modifier = Modifier.scale(scale * infiniteRepeatable)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) {
        Brush.verticalGradient(
            colors = listOf(Color(0xFFF7C376), Color(0xFFF8A832)),
            startY = 0.0f,
            endY = 100.0f
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(Color(0xFFFF9800), Color(0xFFCE7C05)),
            startY = 0.0f,
            endY = 100.0f
        )
    }

    Button(
        onClick = { onClick() },
        interactionSource = interactionSource,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier
            .then(
                Modifier
                    .padding(16.dp)
                    .height(72.dp)
                    .width(148.dp)
                    .background(backgroundColor, shape = CircleShape)
            )
    ) {

        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = quirkyRobotFontFamily, // Apply the custom font here
            color = Color.White
        )
    }
}

@Composable
fun GalaxyBackground() {
    Image(
        painter = painterResource(id = R.drawable.galaxy01),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun GoldenSnakeAnimated(onAnimationFinished: () -> Unit) {
    var isAnimating by remember { mutableStateOf(true) }
    val targetPosition = 300.dp
    val finalPosition = 0.dp
    val positionAnimation by animateDpAsState(
        targetValue = if (isAnimating) targetPosition else finalPosition,
        animationSpec = tween(2000),
        label = "")

    if (positionAnimation == targetPosition) {
        isAnimating = false
    }

    if (positionAnimation == finalPosition) {
        onAnimationFinished.invoke()
    }

    Image(
        painter = painterResource(id = R.drawable.snake_image) ,
        contentDescription = stringResource(id = R.string.snake_image),
        alignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(start = 48.dp, end = 48.dp)
            .offset(y = positionAnimation)
            .animateContentSize()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidSnakeTheme {
        MainScreen()
    }
}