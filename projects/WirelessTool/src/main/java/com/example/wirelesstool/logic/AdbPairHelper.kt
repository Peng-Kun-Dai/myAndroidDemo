package com.example.wirelesstool.logic

import android.content.Context
import android.provider.Settings
import android.util.Log
import android.widget.Toast

object AdbPairHelper {

    /**
     * 通过无线调试配对（Android 11+）
     * @param port 无线调试端口（如5555）
     * @param pairingCode 6位配对码
     */
    fun pairWithWirelessDebugging(
        context: Context,
        port: String,
        pairingCode: String
    ): Boolean {

        val permission = "android.permission.WRITE_SECURE_SETTINGS"

        if (!AdbPermissionManager.checkPermission(
                context, permission
            )
        ) {
            if (!AdbPermissionManager.grantSelfPermission(
                    context, permission
                )
            ) {
                Log.d("PermissionError", "no WRITE_SECURE_SETTINGS")
                return false
            }
        }

//        val autoPort = try {
//            Settings.Global.getInt(context.contentResolver, "adb_wifi_port")
//        } catch (e: Settings.SettingNotFoundException) {
//            null
//        }
//        val autoPort = getPairConfig(context)
//        Log.d("AdbPairHelper", autoPort.toString())

        if (port.isBlank() || pairingCode.isBlank()) {  // 检查空输入
            Toast.makeText(
                context, "端口和配对码不能为空", Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return try {
            val command = "adb pair 127.0.0.1:$port $pairingCode"
//            val command = "adb pair $port $pairingCode"
            val process =
                Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
            process.waitFor() == 0
        } catch (e: Exception) {
            Log.d("pairError", e.toString())
            false
        }
    }

    /**
     * 检查是否已启用无线调试
     */
    fun isWirelessDebuggingEnabled(context: Context): Boolean {
        return try {
            Settings.Global.getInt(
                context.contentResolver, "adb_wifi_enabled", 0
            ) == 1
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }

    fun getPairConfig(context: Context): Int? {


        return try {
//            // 反射获取 Settings.Global 的隐藏字段
//            val field =
//                Settings.Global::class.java.getDeclaredField("ADB_WIFI_PORT")
//            field.isAccessible = true
//            val key = field.get(null) as String //字段实际键名
//            Settings.Global.getInt(context.contentResolver, key)

            val process = Runtime.getRuntime()
                .exec("adb shell settings get global adb_wifi_port")
            process.inputStream.bufferedReader().use {
                it.readLine()?.trim()?.toIntOrNull()
            }
        } catch (e: Exception) {
            Log.d("getPairConfig", e.toString())
            null
        }
    }
}