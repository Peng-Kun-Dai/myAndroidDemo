package com.example.wirelesstool.logic

import android.content.Context
import android.widget.Toast

object AdbPairHelper {
    fun pairWithWirelessDebugging(
        context: Context,
        port: String,
        pairingCode: String
    ) {
        if (port.isBlank() || pairingCode.isBlank()) {  // 检查空输入
            Toast.makeText(
                context,
                "端口和配对码不能为空",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        try {
            val command = "adb pair 127.0.0.1:$port $pairingCode"
            Runtime.getRuntime().exec(command).inputStream.use {
                Toast.makeText(
                    context,
                    "配对成功",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "配对失败: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}