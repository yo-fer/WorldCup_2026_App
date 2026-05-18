package com.example.worldcup2026app.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

// esta clase sella los tipos de texto que podemos enviar desde el backend
//  hacia la UI
sealed class UiText {
    // para textos que vienen directo de la API y no podemos traducir
    data class DynamicString(val value: String) : UiText()

    // para textos que nosotros controlamos
    class StringResource(
        @StringRes val redId: Int,
        vararg val args: Any
    ) : UiText()

    // FUNCIONES DE EVALUACIÓN

    // Función para usar dentro de compose
    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(redId, *args)
        }
    }

    // Función para usar en android
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(redId, *args)
        }
    }
}