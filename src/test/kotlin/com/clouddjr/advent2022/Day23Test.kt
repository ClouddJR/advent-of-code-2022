package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 23")
class Day23Test {
    private val input = """
        ....#..
        ..###.#
        #...#.#
        .#...##
        #.###..
        ##.#.##
        .#..#..
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day23(input).solvePart1()

            assertEquals(110, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day23(resourceAsListOfString("day23.txt")).solvePart1()

            assertEquals(3862, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day23(input).solvePart2()

            assertEquals(20, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day23(resourceAsListOfString("day23.txt")).solvePart2()

            assertEquals(913, answer)
        }
    }
}