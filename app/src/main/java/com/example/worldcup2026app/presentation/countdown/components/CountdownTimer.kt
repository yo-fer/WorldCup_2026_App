package com.example.worldcup2026app.presentation.countdown.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.worldcup2026app.R
import com.example.worldcup2026app.domain.model.Countdown

@Composable
fun CountdownTimer(
    countdown: Countdown,
    modifier: Modifier = Modifier
) {
    // columna principal con un fondo oscuro y bordes redondeados
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título del Reloj
        Text(
            text = if (countdown.isFinished) {
                stringResource(R.string.countdown_finished)
            } else {
                stringResource(R.string.countdown_title)
            },
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Si el mundial no ha comenzado, dibujamos los bloques de tiempo
        if (!countdown.isFinished) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Bloque de Días
                TimeBlock(
                    value = countdown.days.toString(),
                    label = stringResource(R.string.countdown_days)
                )

                // Separador visual
                TimeSeparator()

                // Bloque de Horas
                TimeBlock(
                    value = countdown.hours.toString().padStart(2, '0'),
                    label = stringResource(R.string.countdown_hours)
                )

                TimeSeparator()

                // Bloque de Minutos
                TimeBlock(
                    value = countdown.minutes.toString().padStart(2, '0'),
                    label = stringResource(R.string.countdown_minutes)
                )

                TimeSeparator()

                // Bloque de Segundos
                TimeBlock(
                    value = countdown.seconds.toString().padStart(2, '0'),
                    label = stringResource(R.string.countdown_seconds)
                )
            }
        }
    }
}

// Representa un solo número con su etiqueta abajo
@Composable
private fun TimeBlock(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Black
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// Los dos puntos ":" entre los números
@Composable
private fun TimeSeparator() {
    Text(
        text = ":",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
        textAlign = TextAlign.Center
    )
}
