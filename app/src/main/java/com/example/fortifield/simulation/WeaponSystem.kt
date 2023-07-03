package com.example.fortifield.simulation

class WeaponSystem(val orientationDeterminer: OrientationDeterminer) {
    var isFired: Boolean = false

    fun fire() {
        if (isReady()) {
            isFired = true
        } else {
            isFired = false
        }
    }

    fun isRaised(): Boolean {
        return orientationDeterminer.weaponPosition == "UP"
    }

    fun isReady(): Boolean {
        return orientationDeterminer.direction == "FORWARD" && isRaised()
    }

    fun updateOrientation(newAngle: Double) {
         val weaponSystem = WeaponSystem(OrientationDeterminer(Orientation(System.currentTimeMillis(), 0.0), "FORWARD", "UP"))


    }
}