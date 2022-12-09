package com.clouddjr.advent2022

import kotlin.math.abs
import kotlin.math.sign

class Day09(input: List<String>) {
    private val motions = input.map { it.substringBefore(" ") to it.substringAfter(" ").toInt() }

    private val directions = mapOf(
        "R" to Point(1, 0),
        "U" to Point(0, 1),
        "L" to Point(-1, 0),
        "D" to Point(0, -1),
    )

    fun solvePart1() = visitedTailPositions(numberOfKnots = 2)

    fun solvePart2() = visitedTailPositions(numberOfKnots = 10)

    private fun visitedTailPositions(numberOfKnots: Int): Int {
        val knots = MutableList(numberOfKnots) { Point(0, 0) }
        val visited = mutableSetOf<Point>()

        motions.forEach { (dir, steps) ->
            repeat(steps) {
                knots[0] = knots[0] + directions.getValue(dir)

                knots.indices.windowed(2) { (head, tail) ->
                    if (!knots[tail].isNeighbourOf(knots[head])) {
                        knots[tail] = knots[tail] movedTo knots[head]
                    }
                }

                visited.add(knots.last())
            }
        }

        return visited.size
    }

    private data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(other.x + x, other.y + y)

        infix fun movedTo(other: Point) = this + Point((other.x - x).sign, (other.y - y).sign)

        fun isNeighbourOf(other: Point) = abs(other.x - x) < 2 && abs(other.y - y) < 2
    }
}