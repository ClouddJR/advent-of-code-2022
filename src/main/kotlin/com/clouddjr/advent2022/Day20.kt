package com.clouddjr.advent2022

class Day20(private val input: List<Long>) {
    fun solvePart1() = decrypt(key = 1, times = 1)

    fun solvePart2() = decrypt(key = 811_589_153, times = 10)

    private fun decrypt(key: Long, times: Int): Long {
        val file = input.map { it * key }.withIndex().toMutableList()

        repeat(times) {
            file.indices.forEach { index ->
                val currentIndex = file.indexOfFirst { it.index == index }
                val number = file.removeAt(currentIndex)
                file.add((currentIndex + number.value).mod(file.size), number)
            }
        }

        val zeroIndex = file.indexOfFirst { it.value == 0L }
        return listOf(1000, 2000, 3000).sumOf { file[(zeroIndex + it) % file.size].value }
    }
}