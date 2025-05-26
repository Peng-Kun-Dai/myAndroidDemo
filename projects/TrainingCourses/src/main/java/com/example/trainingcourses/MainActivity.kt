package com.example.trainingcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trainingcourses.model.Topic
import com.example.trainingcourses.ui.theme.TrainingCoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainingCoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    TrainingCoursesTheme {
//        Greeting("Android")
//    }
    CoursesCard(
        Topic(
            R.string.architecture,
            58,
            R.drawable.architecture
        )
    )
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
    CoursesCard(
        Topic(
            R.string.architecture,
            58,
            R.drawable.architecture
        )
    )
}

@Composable
fun CoursesCard(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .height(68.dp),
    ) {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier.height(68.dp),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp
            ).fillMaxSize()
        ) {
            Column {
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    modifier = modifier.padding(
                        bottom = 8.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier.align(Alignment.CenterHorizontally)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = "icon"
                    )
                    Text(
                        text = topic.number.toString(),
                        modifier = modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }

}