package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 17")
class Day17Test {
    private val input = """
        >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day17(input).solvePart1()

            assertEquals(3068, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day17(resourceAsText("day17.txt")).solvePart1()

            assertEquals(3151, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day17(input).solvePart2()

            assertEquals(1_514_285_714_288, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day17(resourceAsText("day17.txt")).solvePart2()

            assertEquals(1_560_919_540_245, answer)
        }
    }
}