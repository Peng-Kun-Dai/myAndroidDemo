package com.example.wirelesstool.logic

import android.content.Context
import android.widget.Toast

object AdbPairHelper {
    fun pairWithWirelessDebugging(
        context: Context,
        port: String,
        pairingCode: String
    ) {
        try {
            val command = "adb pair 127.0.0.1:$port $pairingCode"
            Runtime.getRuntime().exec(
                arrayOf(
                    "sh",
                    "-c",
                    command
                )
            ).inputStream.use {
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