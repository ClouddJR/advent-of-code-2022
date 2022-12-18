package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point3D

class Day18(input: List<String>) {
    private val points = input.map { it.split(",").map(String::toInt) }.map { (x, y, z) -> Point3D(x, y, z) }.toSet()

    fun solvePart1() = points.sumOf { point -> 6 - point.neighbours().count { it in points } }

    fun solvePart2(): Int {
        val xBounds = points.minOf { it.x } - 1..points.maxOf { it.x } + 1
        val yBounds = points.minOf { it.y } - 1..points.maxOf { it.y } + 1
        val zBounds = points.minOf { it.z } - 1..points.maxOf { it.z } + 1

        val toVisit = mutableListOf(Point3D(xBounds.first, yBounds.first, zBounds.first))
        val visited = mutableSetOf<Point3D>()

        var sides = 0
        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeFirst()
            if (current !in visited) {
                current.neighbours()
                    .filter { it.x in xBounds && it.y in yBounds && it.z in zBounds }
                    .forEach { next -> if (next in points) sides++ else toVisit += next }
                visited += current
            }
        }
        return sides
    }
}