package com.example.fortifield.simulation

data class Position(
    var x: Float,
    var y: Float
)


class OrientationDeterminer (
    var position: Position,
    var orientation: Orientation,
    var direction: String, // Framåt, Bakåt, Höger, Vänster
    var weaponPosition: String// Upp, Ner

){

    companion object{
        fun getMockOrientations(): List<OrientationDeterminer> {
            return listOf(
                OrientationDeterminer(Position(0f,0f),Orientation(System.currentTimeMillis() - 10000,10.0),"FORWARD", "UP"),
                OrientationDeterminer(Position(1f,0f),Orientation(System.currentTimeMillis() - 20000,45.0),"FORWARD", "DOWN"),
                OrientationDeterminer(Position(0f,1f),Orientation(System.currentTimeMillis() - 30000,90.0),"BACKWARD", "UP"),
                OrientationDeterminer(Position(1f,1f),Orientation(System.currentTimeMillis() - 40000,135.0),"BACKWARD", "DOWN"),
                OrientationDeterminer(Position(2f,0f),Orientation(System.currentTimeMillis() - 50000,180.0),"RIGHT", "UP"),
                OrientationDeterminer(Position(2f,1f),Orientation(System.currentTimeMillis() - 60000,225.0),"RIGHT", "DOWN"),
                OrientationDeterminer(Position(3f,0f),Orientation(System.currentTimeMillis() - 70000,270.0),"LEFT", "UP"),
                OrientationDeterminer(Position(3f,1f),Orientation(System.currentTimeMillis() - 80000,315.0),"LEFT", "DOWN")
            )

        }

    }

}