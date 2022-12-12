package com.clouddjr.advent2022.utils

data class Point2D(val x: Int, val y: Int) {
    operator fun plus(other: Point2D) = Point2D(other.x + x, other.y + y)

    fun neighbours(): List<Point2D> {
        return arrayOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1))
            .map { (dx, dy) -> Point2D(x + dx, y + dy) }
    }
}