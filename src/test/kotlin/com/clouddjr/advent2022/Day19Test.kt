package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 19")
class Day19Test {
    private val input = """
        Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
        Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day19(input).solvePart1()

            assertEquals(33, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day19(resourceAsListOfString("day19.txt")).solvePart1()

            assertEquals(1675, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day19(input).solvePart2()

            assertEquals(3472, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day19(resourceAsListOfString("day19.txt")).solvePart2()

            assertEquals(6840, answer)
        }
    }
}