package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 9")
class Day09Test {
    private val input = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day09(input).solvePart1()

            assertEquals(13, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day09(resourceAsListOfString("day09.txt")).solvePart1()

            assertEquals(6339, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day09(input).solvePart2()

            assertEquals(1, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day09(resourceAsListOfString("day09.txt")).solvePart2()

            assertEquals(2541, answer)
        }
    }
}