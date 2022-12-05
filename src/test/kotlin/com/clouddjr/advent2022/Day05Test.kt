package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 5")
class Day05Test {
    private val input = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day05(input).solvePart1()

            assertEquals("CMZ", answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day05(resourceAsText("day05.txt")).solvePart1()

            assertEquals("RFFFWBPNS", answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day05(input).solvePart2()

            assertEquals("MCD", answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day05(resourceAsText("day05.txt")).solvePart2()

            assertEquals("CQQBBJFCS", answer)
        }
    }
}