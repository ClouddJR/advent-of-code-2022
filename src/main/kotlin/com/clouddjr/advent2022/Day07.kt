package com.clouddjr.advent2022

class Day07(private val input: List<String>) {
    private val outermost = prepareTree()

    fun solvePart1() = outermost.allDirs().map { it.size() }.filter { it < 100_000 }.sum()

    fun solvePart2(): Int {
        val spaceToFree = 30_000_000 - (70_000_000 - outermost.size())
        return outermost.allDirs().map { it.size() }.sorted().first { it >= spaceToFree }
    }

    private fun prepareTree(): Dir {
        val outermost = Dir("/")

        var current = outermost
        input.drop(1).forEach { line ->
            when {
                line.startsWith("$ cd ..") -> current = current.parent!!
                line.startsWith("$ cd") -> current = current.dirs.first { it.name == line.substringAfter("cd ") }
                line.startsWith("dir") -> current.dirs.add(Dir(line.substringAfter("dir "), current))
                !line.contains("$") -> current.files.add(File(line.substringBefore(" ").toInt()))
            }
        }

        return outermost
    }

    private data class Dir(
        val name: String,
        val parent: Dir? = null,
        val dirs: MutableList<Dir> = mutableListOf(),
        val files: MutableList<File> = mutableListOf()
    ) {
        fun allDirs(): List<Dir> = dirs + dirs.flatMap { it.allDirs() }
        fun size(): Int = files.sumOf { it.size } + dirs.sumOf { it.size() }
    }

    private data class File(val size: Int)
}