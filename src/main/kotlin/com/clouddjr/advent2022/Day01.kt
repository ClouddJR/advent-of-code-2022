package com.clouddjr.advent2022

class Day01(input: String) {
    private val calories = input.split("\n\n")
        .map { it.lines().sumOf(String::toInt) }
        .sortedDescending()

    fun solvePart1() = calories.first()

    fun solvePart2() = calories.take(3).sum()
}