package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D

class Day22(input: List<String>) {
    private val grid = input.dropLast(2)
        .flatMapIndexed { y, line -> line.mapIndexedNotNull { x, c -> if (c == ' ') null else Point2D(x, y) to c } }
        .associate { it }
    private val instructions = Instruction.allFrom(input.last())

    fun solvePart1() = followInstructions(::wrapFlat)

    fun solvePart2() = followInstructions(::wrapCube)

    private fun followInstructions(wrap: (Point2D, Dir) -> Pair<Point2D, Dir>): Int {
        var position = Point2D(grid.keys.filter { it.y == 0 }.minOf { it.x }, 0)
        var dir = Dir.EAST

        instructions.forEach { instruction ->
            when (instruction) {
                is Instruction.Left -> dir = dir.left()
                is Instruction.Right -> dir = dir.right()
                is Instruction.Move -> generateSequence(position to dir) { (p, d) ->
                    val next = p + d.offset
                    when {
                        next in grid && grid[next] == '#' -> p to d
                        next !in grid -> {
                            val (wrapped, wrappedDir) = wrap(p, d)
                            if (grid[wrapped] == '.') wrapped to wrappedDir else p to d
                        }
                        else -> next to d
                    }
                }.take(instruction.steps + 1).last().let { (p, d) -> position = p; dir = d }
            }
        }

        return 1000 * (position.y + 1) + 4 * (position.x + 1) + dir.score
    }

    private fun wrapFlat(position: Point2D, dir: Dir): Pair<Point2D, Dir> {
        val rotatedDir = dir.right().right()
        return generateSequence(position) { it + rotatedDir.offset }.takeWhile { it in grid }.last() to dir
    }

    private fun wrapCube(position: Point2D, dir: Dir): Pair<Point2D, Dir> {
        return when (Triple(dir, position.x / 50, position.y / 50)) {
            Triple(Dir.NORTH, 1, 0) -> Point2D(0, 100 + position.x) to Dir.EAST // 1 -> N
            Triple(Dir.WEST, 1, 0) -> Point2D(0, 149 - position.y) to Dir.EAST // 1 -> W
            Triple(Dir.NORTH, 2, 0) -> Point2D(position.x - 100, 199) to Dir.NORTH // 2 -> N
            Triple(Dir.EAST, 2, 0) -> Point2D(99, 149 - position.y) to Dir.WEST // 2 -> E
            Triple(Dir.SOUTH, 2, 0) -> Point2D(99, -50 + position.x) to Dir.WEST // 2 -> S
            Triple(Dir.EAST, 1, 1) -> Point2D(50 + position.y, 49) to Dir.NORTH // 3 -> E
            Triple(Dir.WEST, 1, 1) -> Point2D(position.y - 50, 100) to Dir.SOUTH // 3 -> W
            Triple(Dir.NORTH, 0, 2) -> Point2D(50, position.x + 50) to Dir.EAST // 4 -> N
            Triple(Dir.WEST, 0, 2) -> Point2D(50, 149 - position.y) to Dir.EAST // 4 -> W
            Triple(Dir.EAST, 1, 2) -> Point2D(149, 149 - position.y) to Dir.WEST // 5 -> E
            Triple(Dir.SOUTH, 1, 2) -> Point2D(49, 100 + position.x) to Dir.WEST // 5 -> S
            Triple(Dir.EAST, 0, 3) -> Point2D(position.y - 100, 149) to Dir.NORTH // 6 -> E
            Triple(Dir.SOUTH, 0, 3) -> Point2D(position.x + 100, 0) to Dir.SOUTH // 6 -> S
            Triple(Dir.WEST, 0, 3) -> Point2D(position.y - 100, 0) to Dir.SOUTH // 6 -> W
            else -> error("Invalid state")
        }
    }

    private sealed class Instruction {
        companion object {
            val pattern = """\d+|[LR]""".toRegex()

            fun allFrom(line: String): List<Instruction> {
                return pattern.findAll(line).map {
                    when (it.value) {
                        "L" -> Left
                        "R" -> Right
                        else -> Move(it.value.toInt())
                    }
                }.toList()
            }
        }

        object Left : Instruction()
        object Right : Instruction()
        data class Move(val steps: Int) : Instruction()
    }

    private enum class Dir(
        val left: () -> Dir,
        val right: () -> Dir,
        val offset: Point2D,
        val score: Int
    ) {
        NORTH(left = { WEST }, right = { EAST }, offset = Point2D(0, -1), score = 3),
        EAST(left = { NORTH }, right = { SOUTH }, offset = Point2D(1, 0), score = 0),
        SOUTH(left = { EAST }, right = { WEST }, offset = Point2D(0, 1), score = 1),
        WEST(left = { SOUTH }, right = { NORTH }, offset = Point2D(-1, 0), score = 2)
    }
}