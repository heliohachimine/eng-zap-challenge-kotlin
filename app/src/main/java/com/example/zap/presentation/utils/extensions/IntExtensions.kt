package com.example.zap.presentation.utils.extensions

fun Int.formatBedrooms(): String {
    return "$this quartos"
}

fun Int.formatBathrooms(): String {
    return "$this banheiros"
}

fun Int.formatParking(): String {
    return "$this vagas"
}
