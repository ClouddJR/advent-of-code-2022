package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D

class Day12(input: List<String>) {
    private val grid = input.flatMapIndexed { y, row -> row.mapIndexed { x, char -> Point2D(x, y) to char } }.toMap()

    fun solvePart1() = reach(destination = 'S')

    fun solvePart2() = reach(destination = 'a')

    private fun reach(destination: Char): Int {
        val startingPoint = grid.entries.first { it.value == 'E' }.key
        val distances = grid.keys.associateWith { Int.MAX_VALUE }.toMutableMap()
        distances[startingPoint] = 0

        val toVisit = mutableListOf(startingPoint)

        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeFirst()
            current.validNeighbours()
                .forEach { neighbour ->
                    val newDistance = distances[current]!! + 1

                    if (grid[neighbour] == destination) return newDistance

                    if (newDistance < distances[neighbour]!!) {
                        distances[neighbour] = newDistance
                        toVisit.add(neighbour)
                    }
                }
        }

        error("No path to given destination")
    }

    private fun Point2D.validNeighbours() = neighbours().filter { neighbour ->
        neighbour in grid && grid[neighbour]!!.elevation - grid[this]!!.elevation >= -1
    }

    private val Char.elevation: Int
        get() = when (this) {
            'S' -> 'a'.code
            'E' -> 'z'.code
            else -> this.code
        }
}