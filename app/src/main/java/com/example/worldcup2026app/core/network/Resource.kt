package com.example.worldcup2026app.core.network

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    // Estado de éxito
    class Success<T>(data: T) : Resource<T>(data = data)

    // Estado de error: fallo de red, límite de API, etc
    class Error<T>(message: String?, data: T? = null) : Resource<T>(
        data = data, message = message)

    // Estado de carga
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
}