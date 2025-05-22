package com.example.happybirthday

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    GreetingImage(
//                        message = stringResource(R.string.happy_birthday_text),
//                        from = "Emma",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    WorkDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun GreetingText(
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 120.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "From: $from",
            fontSize = 20.sp,
            lineHeight = 24.sp,
            //textAlign = TextAlign.End,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .background(Color.White)
                .padding(10.dp)
        )
    }
}

@Composable
fun GreetingImage(
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.androidparty)

    Box() {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.6F,
        )
        GreetingText(
            message = message,
            from = from,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ComposeDemo(modifier: Modifier = Modifier) {

    val image = painterResource(R.drawable.bg_compose_background)
    Column(modifier = Modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Jetpack Compose tutorial",
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun WorkDemo(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier.width(200.dp)
        )
        Text(
            text = "All tasks completed",
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(
                top = 24.dp,
                bottom = 8.dp
            )
        )
        Text(
            text = "Nice work!",
            fontSize = 16.sp
        )
    }
}

@Composable
fun GreetComposeQuadrant(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {

        Row(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5F)
        ) {
            QuadrantCard(
                color = Color(0xFFEADDFF),
                scope = 0.5F,
                text1 = "Text composable",
                text2 = "Displays text and follows the recommended Material Design guidelines.",
            )
            QuadrantCard(
                color = Color(0xFFD0BCFF),
                scope = 1F,
                text1 = "Image composable",
                text2 = "Creates a composable that lays out and draws a given Painter class object.",
            )
        }
        Row(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
        ) {
            QuadrantCard(
                color = Color(0xFFB69DF8),
                scope = 0.5F,
                text1 = "Row composable",
                text2 = "A layout composable that places its children in a horizontal sequence.",
            )
            QuadrantCard(
                color = Color(0xFFF6EDFF),
                scope = 1F,
                text1 = "Column composable",
                text2 = "A layout composable that places its children in a vertical sequence.",
            )
        }
    }
}

@Composable
fun QuadrantCard(
    color: Color,
    scope: Float,
    text1: String,
    text2: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .background(color)
            .fillMaxHeight()
            .fillMaxWidth(scope)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text1,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 16.dp)
        )
        Text(
            text = text2,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun CardDemo(modifier: Modifier = Modifier) {

    Column(
        modifier.fillMaxSize()
    ) {
        Column(
            modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "development"
)
@Composable
fun GreetingPreview() {
    HappyBirthdayTheme {
//        GreetingImage(
//            message = stringResource(R.string.happy_birthday_text),
//            from = "Jayce",
//            modifier = Modifier
//        )

//        Greeting("Android")
//        ComposeDemo()
//        WorkDemo()
//        GreetComposeQuadrant()

        CardDemo()

    }
}
