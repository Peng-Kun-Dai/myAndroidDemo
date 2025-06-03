package com.example.wirelesstool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.wirelesstool.logic.SystemCommand
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


    var apps by remember { mutableStateOf<List<AppInfo>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { // 启动时自动加载
        loadInstalledApps(context) { appList ->
            apps = appList
        }
    }

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
            modifier = Modifier.fillMaxWidth(),
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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "已安装应用 (${apps.size}个)",
                style = MaterialTheme.typography.headlineSmall
            )

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(apps) { app ->
                        AppItem(app = app)
                    }
                }
            }
        }

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

@Composable
fun AppItem(app: AppInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            AsyncImage(
//                model = app.icon,
//                contentDescription = "App Icon",
//                modifier = Modifier.size(48.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    app.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    app.packageName,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}