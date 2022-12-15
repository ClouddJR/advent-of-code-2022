package com.clouddjr.advent2022.utils

import kotlin.math.abs
import kotlin.math.sign

data class Point2D(val x: Int, val y: Int) {
    operator fun plus(other: Point2D) = Point2D(other.x + x, other.y + y)

    fun neighbours(): List<Point2D> {
        return arrayOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1))
            .map { (dx, dy) -> Point2D(x + dx, y + dy) }
    }

    infix fun distanceTo(other: Point2D) = abs(x - other.x) + abs(y - other.y)

    infix fun lineTo(other: Point2D): List<Point2D> {
        val dx = (other.x - x).sign
        val dy = (other.y - y).sign

        val steps = maxOf(abs(x - other.x), abs(y - other.y))

        return (1..steps).scan(this) { last, _ -> Point2D(last.x + dx, last.y + dy) }
    }
}