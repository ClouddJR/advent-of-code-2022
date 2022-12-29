package com.clouddjr.advent2022

class Day25(private val input: List<String>) {
    fun solvePart1() = input.sumOf { it.fromSnafu() }.toSnafu()

    private fun String.fromSnafu(): Long = map { "=-012".indexOf(it) - 2L }.reduce { acc, n -> acc * 5 + n }

    private fun Long.toSnafu() = generateSequence(this) { (it + 2) / 5 }
        .takeWhile { it != 0L }
        .joinToString("") { "${"=-012"[((it + 2) % 5).toInt()]}" }
        .reversed()
}