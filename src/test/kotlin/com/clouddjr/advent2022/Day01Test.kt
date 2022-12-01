package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 1")
class Day01Test {
    private val input = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day01(input).solvePart1()

            assertEquals(24_000, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day01(resourceAsText("day01.txt")).solvePart1()

            assertEquals(69_626, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day01(input).solvePart2()

            assertEquals(45_000, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day01(resourceAsText("day01.txt")).solvePart2()

            assertEquals(206_780, answer)
        }
    }
}