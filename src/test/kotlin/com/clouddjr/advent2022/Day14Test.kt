package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 14")
class Day14Test {
    private val input = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day14(input).solvePart1()

            assertEquals(24, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart1()

            assertEquals(994, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day14(input).solvePart2()

            assertEquals(93, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart2()

            assertEquals(26_283, answer)
        }
    }
}