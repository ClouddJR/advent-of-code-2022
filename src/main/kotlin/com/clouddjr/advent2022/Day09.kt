package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D
import kotlin.math.abs
import kotlin.math.sign

class Day09(input: List<String>) {
    private val motions = input.map { it.substringBefore(" ") to it.substringAfter(" ").toInt() }

    private val directions = mapOf(
        "R" to Point2D(1, 0),
        "U" to Point2D(0, 1),
        "L" to Point2D(-1, 0),
        "D" to Point2D(0, -1),
    )

    fun solvePart1() = visitedTailPositions(numberOfKnots = 2)

    fun solvePart2() = visitedTailPositions(numberOfKnots = 10)

    private fun visitedTailPositions(numberOfKnots: Int): Int {
        val knots = MutableList(numberOfKnots) { Point2D(0, 0) }
        val visited = mutableSetOf<Point2D>()

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

    private infix fun Point2D.movedTo(other: Point2D) = this + Point2D((other.x - x).sign, (other.y - y).sign)

    private fun Point2D.isNeighbourOf(other: Point2D) = abs(other.x - x) < 2 && abs(other.y - y) < 2
}