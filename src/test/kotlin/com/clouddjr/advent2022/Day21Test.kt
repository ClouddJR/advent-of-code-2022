package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 21")
class Day21Test {
    private val input = """
        root: pppw + sjmn
        dbpl: 5
        cczh: sllz + lgvd
        zczc: 2
        ptdq: humn - dvpt
        dvpt: 3
        lfqf: 4
        humn: 5
        ljgn: 2
        sjmn: drzm * dbpl
        sllz: 4
        pppw: cczh / lfqf
        lgvd: ljgn * ptdq
        drzm: hmdt - zczc
        hmdt: 32
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day21(input).solvePart1()

            assertEquals(152, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day21(resourceAsListOfString("day21.txt")).solvePart1()

            assertEquals(24_947_355_373_338, answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day21(input).solvePart2()

            assertEquals(301, answer)
        }

        @Test
        fun `Matches actual`() {
            val answer = Day21(resourceAsListOfString("day21.txt")).solvePart2()

            assertEquals(3_876_907_167_495, answer)
        }
    }
}