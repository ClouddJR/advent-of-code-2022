package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D
import kotlin.math.abs

class Day15(input: List<String>) {
    private val pairs = input.map {
        val x1 = it.substringAfter("x=").substringBefore(",").toInt()
        val y1 = it.substringAfter("y=").substringBefore(":").toInt()
        val x2 = it.substringAfterLast("x=").substringBefore(",").toInt()
        val y2 = it.substringAfterLast("y=").toInt()
        Point2D(x1, y1) to Point2D(x2, y2)
    }

    private val beacons = pairs.map { it.second }.toSet()

    fun solvePart1(row: Int): Int {
        val ranges = getRangesForRow(row)
        return (ranges.minOf { it.first }..ranges.maxOf { it.last }).size() - beacons.filter { it.y == row }.size
    }

    fun solvePart2(): Long {
        (0..40_000_000).forEach { row ->
            getRangesForRow(row).reduce { acc, range -> (acc union range) ?: return (acc.last + 1) * 4_000_000L + row }
        }
        error("No beacon found")
    }

    private fun getRangesForRow(row: Int): List<IntRange> {
        return pairs.mapNotNull { (sensor, beacon) ->
            val distance = sensor distanceTo beacon
            val begin = sensor.x - distance + abs(row - sensor.y)
            val end = sensor.x + distance - abs(row - sensor.y)
            (begin..end).takeUnless { it.isEmpty() }
        }.sortedBy { it.first }
    }

    private fun IntRange.size() = last - first + 1

    private infix fun IntRange.union(other: IntRange): IntRange? {
        return when (this.first <= other.last && this.last >= other.first) {
            true -> IntRange(minOf(first, other.first), maxOf(last, other.last))
            false -> null
        }
    }
}