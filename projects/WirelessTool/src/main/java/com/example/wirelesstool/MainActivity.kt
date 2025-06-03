package com.example.wirelesstool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.wirelesstool.logic.AdbPairHelper
import com.example.wirelesstool.logic.SystemCommand
import com.example.wirelesstool.ui.components.CommandButton
import com.example.wirelesstool.ui.theme.WirelessToolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WirelessToolTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen() {
    val context = LocalContext.current
    var port by remember { mutableStateOf("5555") }
    var pairingCode by remember { mutableStateOf("") }
    var packageName by remember { mutableStateOf("com.example.app") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 无线配对模块
        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = { Text("端口号") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = pairingCode,
            onValueChange = { pairingCode = it },
            label = { Text("配对码") },
            modifier = Modifier.fillMaxWidth()
        )
        CommandButton(
            text = "无线配对",
            onClick = {
                AdbPairHelper.pairWithWirelessDebugging(
                    context,
                    port,
                    pairingCode
                )
            })

        // 系统命令模块
        CommandButton(
            text = "清空图库",
            onClick = { SystemCommand.clearGallery(context) })

        OutlinedTextField(
            value = packageName,
            onValueChange = { packageName = it },
            label = { Text("应用包名") },
            modifier = Modifier.fillMaxWidth()
        )
        CommandButton(
            text = "卸载应用",
            onClick = {
                SystemCommand.uninstallApp(
                    context,
                    packageName
                )
            })
    }
}

