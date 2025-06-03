package com.example.wirelesstool.tool

import android.content.Context
import android.content.pm.PackageManager
import com.example.wirelesstool.model.AppInfo

fun loadInstalledApps(
    context: Context,
    onComplete: (List<AppInfo>) -> Unit
) {
    val pm = context.packageManager
    val apps = mutableListOf<AppInfo>()

    // 获取所有已安装应用
    val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)

    packages.forEach { pkg ->
        apps.add(
            AppInfo(
                name = pkg.applicationInfo!!.loadLabel(pm).toString(),
                packageName = pkg.packageName,
                icon = pkg.applicationInfo!!.loadIcon(pm) // 或使用
                // PackageManagerCompat 获取自适应图标
            )
        )
    }

    onComplete(apps.sortedBy { it.name })
}