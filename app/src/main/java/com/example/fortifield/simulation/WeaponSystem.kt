package com.example.fortifield.simulation

class WeaponSystem( var orientationDeterminer: OrientationDeterminer) {
    var isFired: Boolean = false

    fun fire() {
        isFired = isReady()
    }

    fun isRaised(): Boolean {
        return orientationDeterminer.weaponPosition == "UP"
    }

    fun isReady(): Boolean {
        return orientationDeterminer.direction == "FORWARD" && isRaised()
    }
}
