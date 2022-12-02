package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 2")
class Day02Test {
    private val input = """
        A Y
        B X
        C Z
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day02(input).solvePart1()

            assertEquals(15, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day02(resourceAsListOfString("day02.txt")).solvePart1()

            assertEquals(17_189, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day02(input).solvePart2()

            assertEquals(12, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day02(resourceAsListOfString("day02.txt")).solvePart2()

            assertEquals(13_490, answer)
        }
    }
}