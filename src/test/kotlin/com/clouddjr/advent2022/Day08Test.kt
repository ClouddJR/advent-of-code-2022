package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 8")
class Day08Test {
    private val input = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day08(input).solvePart1()

            assertEquals(21, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day08(resourceAsListOfString("day08.txt")).solvePart1()

            assertEquals(1700, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day08(input).solvePart2()

            assertEquals(8, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day08(resourceAsListOfString("day08.txt")).solvePart2()

            assertEquals(470_596, answer)
        }
    }
}