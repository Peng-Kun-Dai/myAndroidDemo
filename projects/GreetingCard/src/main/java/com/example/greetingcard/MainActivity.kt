package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android1",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

//需在该函数前面添加 @Composable 注解。
//@Composable 函数名称采用首字母大写形式。
//@Composable 函数无法返回任何内容。
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
//        shadowElevation = 4.dp
        color = Color.Gray
    ) {
        Text(
            text = "Hi, my name is $name!",
            color = Color.Black,
            modifier = modifier.padding(24.dp)
        )
    }

}

//预览使用
//无需构建整个应用就能查看可组合函数的外观。如需实现可组合函数的预览，您需要添加 @Composable 和 @Preview 注解。
//@Preview 注解会告知 Android Studio 此可组合函数应显示在此文件的设计视图中。
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("jayce")
    }
}