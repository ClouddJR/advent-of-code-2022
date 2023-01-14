package com.clouddjr.advent2022

import com.clouddjr.advent2022.utils.Point2D

class Day23(input: List<String>) {
    private val grove = Grove.from(input)

    fun solvePart1() = groves().elementAt(10).emptyTiles()

    fun solvePart2() = groves().zipWithNext().takeWhile { it.first.elves != it.second.elves }.count() + 1

    private fun groves() = generateSequence(grove, Grove::performRound)

    private data class Grove(
        val elves: Set<Point2D>,
        val dirs: List<Dir> = listOf(Dir.NORTH, Dir.SOUTH, Dir.WEST, Dir.EAST)
    ) {
        fun performRound(): Grove {
            val (nonMovers, movers) = elves.partition { elf ->
                dirs.none { dir -> dir.neighbours(elf).any(elves::contains) }
            }
            val propositions = movers.associateWith { elf ->
                dirs.firstOrNull { dir -> dir.neighbours(elf).none(elves::contains) }?.move?.invoke(elf) ?: elf
            }
            val validPropositions = propositions.values.groupingBy { it }.eachCount().filter { it.value == 1 }.keys
            val movedOrLeft = propositions.map { (current, next) -> if (next in validPropositions) next else current }

            return Grove(
                elves = nonMovers union movedOrLeft,
                dirs = dirs.rotate()
            )
        }

        fun emptyTiles(): Int {
            val min = Point2D(elves.minOf { it.x }, elves.minOf { it.y })
            val max = Point2D(elves.maxOf { it.x }, elves.maxOf { it.y })
            val xSize = max.x - min.x + 1
            val ySize = max.y - min.y + 1
            return (xSize * ySize) - elves.size
        }

        private fun <T> List<T>.rotate() = drop(1) + first()

        companion object {
            fun from(input: List<String>): Grove {
                return Grove(
                    elves = input.flatMapIndexed { y, line ->
                        line.mapIndexedNotNull { x, c ->
                            if (c != '#') null else Point2D(x, y)
                        }
                    }.toSet()
                )
            }
        }
    }

    private enum class Dir(
        val neighbours: (Point2D) -> Set<Point2D>,
        val move: (Point2D) -> Point2D
    ) {
        NORTH(
            neighbours = { (x, y) -> setOf(Point2D(x - 1, y - 1), Point2D(x, y - 1), Point2D(x + 1, y - 1)) },
            move = { (x, y) -> Point2D(x, y - 1) }
        ),
        SOUTH(
            neighbours = { (x, y) -> setOf(Point2D(x - 1, y + 1), Point2D(x, y + 1), Point2D(x + 1, y + 1)) },
            move = { (x, y) -> Point2D(x, y + 1) }
        ),
        WEST(
            neighbours = { (x, y) -> setOf(Point2D(x - 1, y - 1), Point2D(x - 1, y), Point2D(x - 1, y + 1)) },
            move = { (x, y) -> Point2D(x - 1, y) }
        ),
        EAST(
            neighbours = { (x, y) -> setOf(Point2D(x + 1, y - 1), Point2D(x + 1, y), Point2D(x + 1, y + 1)) },
            move = { (x, y) -> Point2D(x + 1, y) }
        )
    }
}