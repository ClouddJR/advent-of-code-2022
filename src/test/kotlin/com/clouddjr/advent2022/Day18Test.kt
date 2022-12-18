package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 18")
class Day18Test {
    private val input = """
        2,2,2
        1,2,2
        3,2,2
        2,1,2
        2,3,2
        2,2,1
        2,2,3
        2,2,4
        2,2,6
        1,2,5
        3,2,5
        2,1,5
        2,3,5
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day18(input).solvePart1()

            assertEquals(64, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day18(resourceAsListOfString("day18.txt")).solvePart1()

            assertEquals(4364, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day18(input).solvePart2()

            assertEquals(58, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day18(resourceAsListOfString("day18.txt")).solvePart2()

            assertEquals(2508, answer)
        }
    }
}