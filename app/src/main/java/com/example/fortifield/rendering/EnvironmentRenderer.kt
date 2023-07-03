package com.example.fortifield.rendering

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.fortifield.simulation.WeaponSystem
import kotlin.math.cos
import kotlin.math.sin

class EnvironmentRenderer(private val weaponSystem: WeaponSystem) {


    private val soldierPaint = Paint().apply {
        color = Color.GREEN
    }

    private val environmentPaint = Paint().apply {
        color = Color.LTGRAY
    }

    private val miniMapPaint = Paint().apply {
        color = Color.DKGRAY
    }

    fun drawEnvironment(canvas: Canvas) {
        // Draw the environment
        val environmentRect = RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())
        canvas.drawRect(environmentRect, environmentPaint)

        // Draw the soldier
        val soldierX = canvas.width / 2f
        val soldierY = canvas.height / 2f
        canvas.drawCircle(soldierX, soldierY, 50f, soldierPaint)
        // Draw the weapon
        val weaponAngle = Math.toRadians(weaponSystem.orientationDeterminer.orientation.angle.toDouble())
        val weaponX = soldierX + 50 * cos(weaponAngle.toFloat())
        val weaponY = soldierY + 50 * sin(weaponAngle.toFloat())
        canvas.drawLine(soldierX, soldierY, weaponX, weaponY, soldierPaint)

        val miniMapRect = RectF(0f, 0f, canvas.width / 4f, canvas.height / 4f)
        canvas.drawRect(miniMapRect, miniMapPaint)

        // Draw the soldier on the mini-map
        val miniMapSoldierX = miniMapRect.width() / 2f
        val miniMapSoldierY = miniMapRect.height() / 2f
        canvas.drawCircle(miniMapSoldierX, miniMapSoldierY, 10f, soldierPaint)

        // Draw the weapon on the mini-map
        val miniMapWeaponX = miniMapSoldierX + 10 * cos(weaponAngle.toFloat())
        val miniMapWeaponY = miniMapSoldierY + 10 * sin(weaponAngle.toFloat())
        canvas.drawLine(miniMapSoldierX, miniMapSoldierY, miniMapWeaponX, miniMapWeaponY, soldierPaint)

    }
}
