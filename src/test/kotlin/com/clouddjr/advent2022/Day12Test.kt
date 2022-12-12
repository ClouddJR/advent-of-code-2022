package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 12")
class Day12Test {
    private val input = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day12(input).solvePart1()

            assertEquals(31, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart1()

            assertEquals(456, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day12(input).solvePart2()

            assertEquals(29, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart2()

            assertEquals(454, answer)
        }
    }
}