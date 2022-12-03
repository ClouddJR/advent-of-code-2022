package com.clouddjr.advent2022

class Day03(private val input: List<String>) {
    fun solvePart1() = input.map { it.take(it.length / 2) to it.substring(it.length / 2) }
        .map { (it.first.toSet() intersect it.second.toSet()).single() }
        .sumOf(Char::getPriority)

    fun solvePart2() = input.map(String::toSet)
        .chunked(3)
        .map { it.reduce(Set<Char>::intersect).single() }
        .sumOf(Char::getPriority)
}

private fun Char.getPriority() = (('a'..'z') + ('A'..'Z')).indexOf(this) + 1