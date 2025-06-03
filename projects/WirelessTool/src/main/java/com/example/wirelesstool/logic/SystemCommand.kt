package com.example.wirelesstool.logic

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

object SystemCommand {
    // 清空图库
    fun clearGallery(context: Context) {
        try {
            Runtime.getRuntime().exec("rm -rf /sdcard/DCIM/*")
            Runtime.getRuntime().exec("rm -rf /sdcard/Pictures/*")
            Toast.makeText(
                context,
                "图库已清空",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "清空失败: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 静默卸载应用
    fun uninstallApp(
        context: Context,
        packageName: String
    ) {
        try {
            Runtime.getRuntime().exec("pm uninstall --user 0 $packageName")
            Toast.makeText(
                context,
                "卸载成功",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "卸载失败: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

fun checkPermission(
    context: Context,
    permission: String
): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}