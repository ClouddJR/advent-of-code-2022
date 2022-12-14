package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D

class Day14(input: List<String>) {
    private val rocks = input.flatMap { path ->
        path.split(" -> ")
            .map { it.split(",") }
            .map { (x, y) -> Point2D(x.toInt(), y.toInt()) }
            .zipWithNext()
            .flatMap { (first, second) -> (first lineTo second) }
    }.toSet()

    private val resting = mutableSetOf<Point2D>()

    private val initial = Point2D(500, 0)

    fun solvePart1(): Int {
        val last = rocks.maxOf { it.y }

        var current = initial
        while (current.y != last) {
            current = current.next() ?: initial.also { resting.add(current) }
        }

        return resting.size
    }

    fun solvePart2(): Int {
        val floor = rocks.maxOf { it.y } + 2

        var current = initial
        while (initial !in resting) {
            val next = current.next()
            current = when (next == null || next.y == floor) {
                true -> initial.also { resting.add(current) }
                else -> next
            }
        }

        return resting.size
    }

    private fun Point2D.next() = listOf(
        Point2D(x, y + 1),
        Point2D(x - 1, y + 1),
        Point2D(x + 1, y + 1)
    ).firstOrNull { it !in rocks && it !in resting }
}