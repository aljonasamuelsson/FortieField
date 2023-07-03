package com.example.fortifield.simulation


class OrientationDeterminer (
    var orientation: Orientation,
    val direction: String, // Framåt, Bakåt, Höger, Vänster
    var weaponPosition: String// Upp, Ner

){

    companion object{
        fun getMockOrientations(): List<OrientationDeterminer> {
            return listOf(
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 10000,10.0),"FORWARD", "UP"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 20000,45.0),"FORWARD", "DOWN"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 30000,90.0),"BACKWARD", "UP"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 40000,135.0),"BACKWARD", "DOWN"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 50000,180.0),"RIGHT", "UP"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 60000,225.0),"RIGHT", "DOWN"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 70000,270.0),"LEFT", "UP"),
                OrientationDeterminer(Orientation(System.currentTimeMillis() - 80000,315.0),"LEFT", "DOWN")
            )

        }

    }

}