package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import com.clouddjr.advent2022.Resources.resourceAsString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 6")
class Day06Test {
    private val input = """
        mjqjpqmgbljsphdztnvjfqwrcgsmlb
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day06(input).solvePart1()

            assertEquals(7, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day06(resourceAsString("day06.txt")).solvePart1()

            assertEquals(1544, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day06(input).solvePart2()

            assertEquals(19, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day06(resourceAsString("day06.txt")).solvePart2()

            assertEquals(2145, answer)
        }
    }
}