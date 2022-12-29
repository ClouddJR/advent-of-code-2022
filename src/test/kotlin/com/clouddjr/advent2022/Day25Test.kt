package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 25")
class Day25Test {
    private val input = """
        1=-0-2
        12111
        2=0=
        21
        2=01
        111
        20012
        112
        1=-1=
        1-12
        12
        1=
        122
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day25(input).solvePart1()

            assertEquals("2=-1=0", answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day25(resourceAsListOfString("day25.txt")).solvePart1()

            assertEquals("122-0==-=211==-2-200", answer)
        }
    }
}