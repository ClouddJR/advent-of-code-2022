package com.clouddjr.advent2022

class Day06(private val input: String) {
    fun solvePart1() = getMarker(size = 4)

    fun solvePart2() = getMarker(size = 14)

    private fun getMarker(size: Int) =
        (size..input.length).first { i -> input.substring(i - size, i).toSet().size == size }
}