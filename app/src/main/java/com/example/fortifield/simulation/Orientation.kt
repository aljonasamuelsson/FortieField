package com.example.fortifield.simulation

data class Orientation(
    val timestamp: Long, // Tid när rörelsen gjordes
    val angle: Double // Vapnets orienteringsvinkel i grader
)