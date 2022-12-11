package com.clouddjr.advent2022

class Day11(input: String) {
    private val monkeys = input.split("\n\n").map { Monkey.from(it.lines()) }

    fun solvePart1() = keepAway(rounds = 20, manageWorryLevel = { level, _ -> level / 3 })

    fun solvePart2() = keepAway(rounds = 10_000, manageWorryLevel = { level, modulus -> level % modulus })

    private fun keepAway(rounds: Int, manageWorryLevel: (Long, Int) -> Long): Long {
        val modulus = monkeys.map { it.divisor }.reduce(Int::times)

        repeat(rounds) {
            monkeys.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkey.inspected++
                    val new = manageWorryLevel(monkey.operation(item), modulus)
                    monkeys[if (new % monkey.divisor == 0L) monkey.trueMonkey else monkey.falseMonkey].items.add(new)
                }
                monkey.items.clear()
            }
        }

        return monkeys.map { it.inspected.toLong() }.sortedDescending().take(2).reduce(Long::times)
    }

    private data class Monkey(
        var inspected: Int = 0,
        val items: MutableList<Long>,
        val operation: (Long) -> Long,
        val divisor: Int,
        val trueMonkey: Int,
        val falseMonkey: Int
    ) {
        companion object {
            fun from(definition: List<String>): Monkey {
                val operation = { previous: Long ->
                    val argument = definition[2].substringAfterLast(" ")
                        .let { if (it == "old") previous else it.toLong() }

                    when (definition[2].substringAfter("old ").first()) {
                        '+' -> previous + argument
                        '*' -> previous * argument
                        else -> previous
                    }
                }

                return Monkey(
                    items = definition[1].substringAfter("items: ").split(", ").map { it.toLong() }.toMutableList(),
                    operation = operation,
                    divisor = definition[3].substringAfter("by ").toInt(),
                    trueMonkey = definition[4].substringAfter("monkey ").toInt(),
                    falseMonkey = definition[5].substringAfter("monkey ").toInt()
                )
            }
        }
    }
}