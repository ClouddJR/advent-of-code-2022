package com.clouddjr.advent2022

class Day10(input: String) {
    private val values = input.split(" ", "\n")
        .map { if (it.last().isDigit()) it.toInt() else 0 }
        .scan(1, Int::plus).dropLast(1)

    fun solvePart1() = values.mapIndexed { cycle, register ->
        if ((cycle + 1) % 40 == 20) (cycle + 1) * register else 0
    }.sum()

    fun solvePart2() = values.foldIndexed("") { cycle, image, register ->
        image + if (cycle % 40 in register - 1..register + 1) "#" else "."
    }.chunked(40).joinToString("\n")
}