package com.example.superheroes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.superheroes.R

data class Hero(
    @DrawableRes
    val imageRes: Int,
    @StringRes
    val nameRes: Int,
    @StringRes
    val descriptionRes: Int
)

val heroes = listOf(
    Hero(R.drawable.android_superhero1, R.string.hero1, R.string.description1),
    Hero(R.drawable.android_superhero2, R.string.hero2, R.string.description2),
    Hero(R.drawable.android_superhero3, R.string.hero3, R.string.description3),
    Hero(R.drawable.android_superhero4, R.string.hero4, R.string.description4),
    Hero(R.drawable.android_superhero5, R.string.hero5, R.string.description5),
    Hero(R.drawable.android_superhero6, R.string.hero6, R.string.description6),
)