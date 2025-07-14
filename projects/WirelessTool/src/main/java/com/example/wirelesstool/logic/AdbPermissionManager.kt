package com.example.wirelesstool.logic

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object AdbPermissionManager {

    /**
     * 通过ADB授予自身权限（需用户手动执行一次）
     * 示例：adb shell pm grant your.package android.permission.WRITE_SECURE_SETTINGS
     */
    fun grantSelfPermission(
        context: Context,
        permission: String
    ): Boolean {
        return try {
            val command = "pm grant ${context.packageName} $permission"
            Runtime.getRuntime().exec(arrayOf("sh", "-c", command))
                .waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 检查是否已拥有权限
     */
    fun checkPermission(
        context: Context,
        permission: String
    ): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}