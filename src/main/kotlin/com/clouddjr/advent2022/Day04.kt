package com.clouddjr.advent2022

class Day04(input: List<String>) {
    private val pattern = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

    private val pairs = input.map { pattern.matchEntire(it)!!.destructured }
        .map { (x1, x2, y1, y2) -> (x1.toInt()..x2.toInt()).toSet() to (y1.toInt()..y2.toInt()).toSet() }

    fun solvePart1() = pairs.count { it.first.containsAll(it.second) || it.second.containsAll(it.first) }

    fun solvePart2() = pairs.count { (it.first intersect it.second).isNotEmpty() }
}