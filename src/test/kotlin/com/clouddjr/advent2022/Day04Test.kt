package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 4")
class Day04Test {
    private val input = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day04(input).solvePart1()

            assertEquals(2, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day04(resourceAsListOfString("day04.txt")).solvePart1()

            assertEquals(547, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day04(input).solvePart2()

            assertEquals(4, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day04(resourceAsListOfString("day04.txt")).solvePart2()

            assertEquals(843, answer)
        }
    }
}