package com.davidups.starwars.core.extensions

fun String.Companion.empty() = ""

fun String.Companion.randomImage() = "https://picsum.photos/id/${(0..300).random()}/1920/1080"
