import argparse
import requests
import os

parser = argparse.ArgumentParser(description="Create a template for a given day.")
parser.add_argument("-d", "--day", type=int)
parser.add_argument("-i", "--input", choices=["string", "listString", "listInt", "listLong"], default="listString")
args = parser.parse_args()

day = f"0{args.day}" if (args.day < 10) else f"{args.day}"
input = args.input

match input:
    case "string":
        inputType = "String"
        exampleInputMethods = ""
        loadResourceMethod = "resourceAsText"
    case "listString":
        inputType = "List<String>"
        exampleInputMethods = ".lines()"
        loadResourceMethod = "resourceAsListOfString"
    case "listInt":
        inputType = "List<Int>"
        exampleInputMethods = ".lines().map { it.toInt() }"
        loadResourceMethod = "resourceAsListOfInt"
    case "listLong":
        inputType = "List<Long>"
        exampleInputMethods = ".lines().map { it.toLong() }"
        loadResourceMethod = "resourceAsListOfLong"

# Create a DayXX class
f = open(f"../src/main/kotlin/com/clouddjr/advent2022/Day{day}.kt", "w")
fileContent = f'''package com.clouddjr.advent2022

class Day{day}(input: {inputType}) {{
    fun solvePart1(): Int {{
        return 0
    }}

    fun solvePart2(): Int {{
        return 0
    }}
}}'''
f.write(fileContent)

# Create a DayXXTest class
f = open(f"../src/test/kotlin/com/clouddjr/advent2022/Day{day}Test.kt", "w")
fileContent = f'''package com.clouddjr.advent2022

import com.clouddjr.advent2022.Resources.{loadResourceMethod}
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day {args.day}")
class Day{day}Test {{
    private val input = """""".trimIndent(){exampleInputMethods}

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {{
        @Test
        fun `Matches example`() {{
            val answer = Day{day}(input).solvePart1()

            assertEquals(1, answer)
        }}

        @Test
        fun `Matches actual`() {{
            val answer = Day{day}({loadResourceMethod}("day{day}.txt")).solvePart1()

            assertEquals(1, answer)
        }}
    }}

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {{
        @Test
        fun `Matches example`() {{
            val answer = Day{day}(input).solvePart2()

            assertEquals(1, answer)
        }}

        @Test
        fun `Matches actual`() {{
            val answer = Day{day}({loadResourceMethod}("day{day}.txt")).solvePart2()

            assertEquals(1, answer)
        }}
    }}
}}'''
f.write(fileContent)

# Create a dayXX.txt file for the puzzle input and download it from the website
f = open(f"../src/main/resources/day{day}.txt", "w")

session = os.environ.get("AOC_SESSION")
res = requests.get(f"https://adventofcode.com/2022/day/{args.day}/input", cookies={'session': session})

f.write(res.text.strip())