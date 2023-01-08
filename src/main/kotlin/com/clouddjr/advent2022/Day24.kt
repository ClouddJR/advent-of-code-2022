package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D

class Day24(input: List<String>) {
    private val initialValley = Valley.from(input)
    private val start = Point2D(input.first().indexOf('.'), 0)
    private val goal = Point2D(input.last().indexOf('.'), input.lastIndex)

    fun solvePart1() = reachTheGoal(start, goal, initialValley).first

    fun solvePart2(): Int {
        val (firstMinutes, afterFirst) = reachTheGoal(start, goal, initialValley)
        val (secondMinutes, afterSecond) = reachTheGoal(goal, start, afterFirst)
        val (thirdMinutes, _) = reachTheGoal(start, goal, afterSecond)
        return firstMinutes + secondMinutes + thirdMinutes
    }

    private fun reachTheGoal(start: Point2D, goal: Point2D, initial: Valley): Pair<Int, Valley> {
        var minutes = 0
        var valley = initial
        var batch = setOf(start)

        while (true) {
            minutes++
            valley = valley.moved()
            batch = buildSet {
                batch.forEach { current ->
                    addAll(
                        current.neighbours()
                            .onEach { if (it == goal) return minutes to valley }
                            .filter(valley::isSafe)
                    )
                    if (valley.isSafe(current) || current == start) add(current)
                }
            }
        }
    }

    private data class Valley(val blizzards: List<Blizzard>, val boundary: Point2D) {
        private val unsafePoints = blizzards.map { it.point }.toSet()

        fun moved() = copy(blizzards = blizzards.map { it.moved(boundary) })

        fun isSafe(point: Point2D): Boolean {
            return point !in unsafePoints && point.x in 1 until boundary.x && point.y in 1 until boundary.y
        }

        companion object {
            fun from(input: List<String>): Valley {
                return Valley(
                    blizzards = input.flatMapIndexed { y, line ->
                        line.mapIndexedNotNull { x, char ->
                            when (char) {
                                '>' -> Blizzard(Point2D(x, y), Point2D(1, 0))
                                '<' -> Blizzard(Point2D(x, y), Point2D(-1, 0))
                                'v' -> Blizzard(Point2D(x, y), Point2D(0, 1))
                                '^' -> Blizzard(Point2D(x, y), Point2D(0, -1))
                                else -> null
                            }
                        }
                    },
                    boundary = Point2D(input.last().lastIndex, input.lastIndex)
                )
            }
        }
    }

    private data class Blizzard(val point: Point2D, val dir: Point2D) {
        fun moved(boundary: Point2D): Blizzard {
            val next = point + dir
            return copy(
                point = when {
                    next.x == 0 -> Point2D(boundary.x - 1, next.y)
                    next.y == 0 -> Point2D(next.x, boundary.y - 1)
                    next.x == boundary.x -> Point2D(1, next.y)
                    next.y == boundary.y -> Point2D(next.x, 1)
                    else -> next
                }
            )
        }
    }
}