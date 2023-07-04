package com.example.fortifield.simulation

class Soldier(var orientationDeterminer: OrientationDeterminer) {
    // Other properties and methods of the Soldier class

    fun updateOrientation(newOrientationDeterminer: OrientationDeterminer) {
        orientationDeterminer.orientation = newOrientationDeterminer.orientation
        orientationDeterminer.direction = newOrientationDeterminer.direction
        orientationDeterminer.weaponPosition = newOrientationDeterminer.weaponPosition
    }
}