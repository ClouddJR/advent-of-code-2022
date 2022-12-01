package com.clouddjr.advent2022

class Day01(input: String) {
    private val elves = input.split("\n\n").map { group -> Elf(group.split("\n").map { it.toInt() }) }

    fun solvePart1() = elves.maxOf { it.calories.sum() }

    fun solvePart2() = elves.map { it.calories.sum() }.sortedDescending().take(3).sum()

    private data class Elf(val calories: List<Int>)
}