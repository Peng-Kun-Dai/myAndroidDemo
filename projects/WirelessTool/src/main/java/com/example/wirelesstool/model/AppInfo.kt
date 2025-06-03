package com.example.wirelesstool.model

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: Any // 可以是 Drawable 或 Uri
)