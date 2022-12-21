package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfLong
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 20")
class Day20Test {
    private val input = """
        1
        2
        -3
        3
        -2
        0
        4
    """.trimIndent().lines().map { it.toLong() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day20(input).solvePart1()

            assertEquals(3, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day20(resourceAsListOfLong("day20.txt")).solvePart1()

            assertEquals(18_257, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day20(input).solvePart2()

            assertEquals(1_623_178_306, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day20(resourceAsListOfLong("day20.txt")).solvePart2()

            assertEquals(4_148_032_160_983, answer)
        }
    }
}