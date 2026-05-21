package com.example.worldcup2026app.domain.usecase

import com.example.worldcup2026app.domain.model.Countdown
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.TimeZone

class GetCountdownTickUseCase {
    // Fecha de inauguración

    private val targetDateInMillis: Long = Calendar.getInstance(
        TimeZone.getTimeZone("UTC")).apply {
        set(2026, Calendar.JUNE, 11, 19, 0, 0) // 11 de Junio de 2026, 19:00 UTC
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    operator fun invoke(): Flow<Countdown> = flow {
        while (true) {
            val currentTime = System.currentTimeMillis()
            val diffInMillis = targetDateInMillis - currentTime

            if (diffInMillis <= 0) {
                // el mundial comenzó
                emit(Countdown(0, 0, 0, 0, isFinished = true))
                break
            } else {
                val seconds = (diffInMillis / 1000) % 60
                val minutes = (diffInMillis / (1000 * 60)) % 60
                val hours = (diffInMillis / (1000 * 60 * 60)) % 24
                val days = (diffInMillis / (1000 * 60 * 60 * 24))

                emit(
                    Countdown(
                        days = days.toInt(),
                        hours = hours.toInt(),
                        minutes = minutes.toInt(),
                        seconds = seconds.toInt()
                    )
                )
            }
            // esperamos un segundo antes de la siguiente emisión
            delay(1000)
        }
    }
}