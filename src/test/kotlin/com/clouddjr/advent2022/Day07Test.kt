package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 7")
class Day07Test {
    private val input = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day07(input).solvePart1()

            assertEquals(95_437, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart1()

            assertEquals(1_454_188, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day07(input).solvePart2()

            assertEquals(24_933_642, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart2()

            assertEquals(4_183_246, answer)
        }
    }
}