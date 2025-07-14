package com.example.wirelesstool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.wirelesstool.logic.AdbPairHelper
import com.example.wirelesstool.logic.AdbPairHelper.isWirelessDebuggingEnabled
import com.example.wirelesstool.logic.SystemOperations
import com.example.wirelesstool.model.AppInfo
import com.example.wirelesstool.tool.loadInstalledApps
import com.example.wirelesstool.ui.components.CommandButton
import com.example.wirelesstool.ui.theme.WirelessToolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WirelessToolTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val context = LocalContext.current
    var port by remember { mutableStateOf("5555") }
    var pairingCode by remember { mutableStateOf("") }
    var packageName by remember { mutableStateOf("com.example.app") }
    var log by remember { mutableStateOf("等待操作...") }

    Column(modifier = Modifier.padding(16.dp)) {
        // 无线调试配对
        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = { Text("无线调试端口") })
        OutlinedTextField(
            value = pairingCode,
            onValueChange = { pairingCode = it },
            label = { Text("配对码") })
        Button(onClick = {
            if (!isWirelessDebuggingEnabled(context)) {
                log = "请先开启无线调试"
                return@Button
            }

            log = if (AdbPairHelper.pairWithWirelessDebugging(
                    context, port, pairingCode
                )
            ) {
                "配对成功！"
            } else {
                "配对失败，请检查端口和配对码"
            }
        }) {
            Text("无线配对")
        }

        /*        // 敏感操作
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Button(onClick = {
                    log = if (SystemOperations.clearGallery(context)) {
                        "图库已清空"
                    } else {
                        "清空失败，请检查权限"
                    }
                }) {
                    Text("清空图库")
                }
                OutlinedTextField(
                    value = packageName,
                    onValueChange = { packageName = it },
                    label = { Text("要卸载的应用包名") })
                Button(onClick = {
                    log = if (SystemOperations.uninstallApp(packageName)) {
                        "$packageName 卸载成功"
                    } else {
                        "卸载失败，请检查包名和权限"
                    }
                }) {
                    Text("卸载应用")
                }*/

        // 日志输出
        Text(
            text = log, modifier = Modifier.padding(top = 16.dp)
        )
    }
}