package com.example.droidquest.models

data class Question(
    val stringId: Int,
    val answer: Boolean,
    var wasDecided: Boolean = false
)