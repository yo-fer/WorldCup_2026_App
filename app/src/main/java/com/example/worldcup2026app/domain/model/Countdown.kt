package com.example.worldcup2026app.domain.model

data class Countdown(
    val days: Int,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
    val isFinished: Boolean = false
)
