package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 24")
class Day24Test {
    private val input = """
        #.######
        #>>.<^<#
        #.<..<<#
        #>v.><>#
        #<^v^^>#
        ######.#
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day24(input).solvePart1()

            assertEquals(18, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day24(resourceAsListOfString("day24.txt")).solvePart1()

            assertEquals(257, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day24(input).solvePart2()

            assertEquals(54, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day24(resourceAsListOfString("day24.txt")).solvePart2()

            assertEquals(828, answer)
        }
    }
}