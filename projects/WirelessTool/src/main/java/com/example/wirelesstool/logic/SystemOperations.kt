package com.example.wirelesstool.logic

import android.content.Context
import android.widget.Toast

object SystemOperations {

    /**
     * 清空图库（需要 WRITE_MEDIA_STORAGE 权限）
     */
    fun clearGallery(context: Context): Boolean {
        if (!AdbPermissionManager.checkPermission(
                context, "android.permission.WRITE_MEDIA_STORAGE"
            )
        ) {
            return false
        }
        return try {
            Runtime.getRuntime().exec("rm -rf /sdcard/DCIM/*")
                .waitFor() == 0 && Runtime.getRuntime()
                .exec("rm -rf /sdcard/Pictures/*").waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 静默卸载应用（需要 DELETE_PACKAGES 权限）
     */
    fun uninstallApp(packageName: String): Boolean {
        return try {
            Runtime.getRuntime().exec("pm uninstall --user 0 $packageName")
                .waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}