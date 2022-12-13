package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 13")
class Day13Test {
    private val input = """
        [1,1,3,1,1]
        [1,1,5,1,1]

        [[1],[2,3,4]]
        [[1],4]

        [9]
        [[8,7,6]]

        [[4,4],4,4]
        [[4,4],4,4,4]

        [7,7,7,7]
        [7,7,7]

        []
        [3]

        [[[]]]
        [[]]

        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day13(input).solvePart1()

            assertEquals(13, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day13(resourceAsText("day13.txt")).solvePart1()

            assertEquals(6101, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day13(input).solvePart2()

            assertEquals(140, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day13(resourceAsText("day13.txt")).solvePart2()

            assertEquals(21_909, answer)
        }
    }
}