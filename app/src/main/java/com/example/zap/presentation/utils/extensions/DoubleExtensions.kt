package com.example.zap.presentation.utils.extensions

import java.text.NumberFormat
import java.util.*

fun Double.toCurrencyBr(): String {
    val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return  formatter.format(this)
}

fun Double.formatArea(): String {
    return "$this m2"
}
