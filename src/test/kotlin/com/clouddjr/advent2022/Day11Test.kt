package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 11")
class Day11Test {
    private val input = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3

        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0

        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3

        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day11(input).solvePart1()

            assertEquals(10_605, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day11(resourceAsText("day11.txt")).solvePart1()

            assertEquals(107_822, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day11(input).solvePart2()

            assertEquals(2_713_310_158, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day11(resourceAsText("day11.txt")).solvePart2()

            assertEquals(27_267_163_742, answer)
        }
    }
}