package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D

class Day17(jets: String) {
    private val cave = Cave(jets)

    fun solvePart1() = findTowerHeight(targetRocks = 2022)

    fun solvePart2() = findTowerHeight(targetRocks = 1_000_000_000_000)

    private fun findTowerHeight(targetRocks: Long): Long {
        val cache = mutableMapOf<State, Pair<Long, Int>>()

        for (rock in 0 until targetRocks) {
            cave.addShape()

            val state = cave.getCurrentState()
            if (state in cache) {
                val (rocksAtLoopStart, heightAtLoopStart) = cache.getValue(state)
                val rocksPerLoop = rock - rocksAtLoopStart
                val loopHeight = (cave.height() - heightAtLoopStart)
                val remainingLoops = (targetRocks - rock) / rocksPerLoop
                val remainingRocks = (targetRocks - rock) % rocksPerLoop
                val remainingHeight = cache.values
                    .first { (rock, _) -> rock == rocksAtLoopStart + remainingRocks - 1 }.second - heightAtLoopStart

                return cave.height() + remainingLoops * loopHeight + remainingHeight
            }
            cache[state] = rock to cave.height()
        }

        error("No repeating pattern")
    }

    private data class State(val top: List<Int>, val jetIndex: Int, val shapeIndex: Long)

    private class Cave(private val jets: String) {
        private val points = (0..6).map { Point2D(it, -1) }.toMutableSet()

        private var jetIndex = 0
            set(value) {
                field = value % jets.length
            }

        private var shapeIndex = 0L
            set(value) {
                field = value % 5
            }

        fun height() = points.maxOf { it.y + 1 }

        fun addShape() {
            var shape = nextShape()
            while (true) {
                val pushed = shape.push(jets[jetIndex++])
                if (!pushed.collides()) shape = pushed

                val fallen = shape.fall()
                if (fallen.collides()) break else shape = fallen
            }
            points += shape.points
        }

        fun getCurrentState() = State(
            top = points
                .groupBy { it.x }
                .entries
                .sortedBy { it.key }
                .map { it.value.maxBy { point -> point.y } }
                .let { points ->
                    val height = height()
                    points.map { point -> point.y - height }
                },
            jetIndex = jetIndex,
            shapeIndex = shapeIndex
        )

        private fun nextShape(): Shape {
            val bottom = (points.maxOfOrNull { p -> p.y } ?: -1) + 4
            val shape = Shape.create(shapeIndex++)
            return shape.copy(points = shape.points.map { it + Point2D(0, bottom) })
        }

        private fun Shape.collides() = points.intersect(this@Cave.points).isNotEmpty() || points.any { it.x !in (0..6) }
    }

    private data class Shape(val points: List<Point2D>) {
        fun push(dir: Char) = when (dir) {
            '>' -> copy(points = points.map { it.copy(x = it.x + 1) })
            '<' -> copy(points = points.map { it.copy(x = it.x - 1) })
            else -> error("Unsupported operation")
        }

        fun fall() = copy(points = points.map { it.copy(y = it.y - 1) })

        companion object {
            fun create(order: Long): Shape = Shape(
                when (order) {
                    0L -> listOf(Point2D(2, 0), Point2D(3, 0), Point2D(4, 0), Point2D(5, 0))
                    1L -> listOf(Point2D(3, 0), Point2D(2, 1), Point2D(3, 1), Point2D(4, 1), Point2D(3, 2))
                    2L -> listOf(Point2D(2, 0), Point2D(3, 0), Point2D(4, 0), Point2D(4, 1), Point2D(4, 2))
                    3L -> listOf(Point2D(2, 0), Point2D(2, 1), Point2D(2, 2), Point2D(2, 3))
                    4L -> listOf(Point2D(2, 0), Point2D(2, 1), Point2D(3, 0), Point2D(3, 1))
                    else -> error("Unsupported state")
                }
            )
        }
    }
}