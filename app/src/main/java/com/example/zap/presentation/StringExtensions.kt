package com.example.zap.presentation

fun String.formatBedrooms(): String {
    return "$this quartos"
}

fun String.formatBathrooms(): String {
    return "$this banheiros"
}

fun String.formatArea(): String {
    return "$this m2"
}

fun String.formatParking(): String {
    return "$this vagas"
}
