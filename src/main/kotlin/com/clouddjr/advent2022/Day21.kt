package com.clouddjr.advent2022

class Day21(input: List<String>) {
    private val monkeys = input.map { it.split(": ") }.associate { (monkey, job) -> monkey to job }

    private val operations = mapOf(
        "+" to { a: Long, b: Long -> a + b },
        "-" to { a: Long, b: Long -> a - b },
        "*" to { a: Long, b: Long -> a * b },
        "/" to { a: Long, b: Long -> a / b }
    )

    private val leftReversedOperations = operations.mapKeys {
        when (it.key) {
            "+" -> "-"
            "-" -> "+"
            "*" -> "/"
            else -> "*"
        }
    }

    private val rightReversedOperations = mapOf(
        "+" to { a: Long, b: Long -> a - b },
        "-" to { a: Long, b: Long -> b - a },
        "*" to { a: Long, b: Long -> a / b },
        "/" to { a: Long, b: Long -> b / a }
    )

    fun solvePart1() = findNumberToYell("root")

    fun solvePart2() = findMyNumberToYell()

    private fun findNumberToYell(monkey: String): Long {
        val job = monkeys[monkey]!!
        return when (val number = job.toLongOrNull()) {
            null -> {
                val first = job.substringBefore(" ")
                val second = job.substringAfterLast(" ")
                val op = job.substringAfter(" ").substringBefore(" ")
                operations[op]!!(findNumberToYell(first), findNumberToYell(second))
            }
            else -> number
        }
    }

    private fun findMyNumberToYell(): Long {
        val rootJob = monkeys["root"]!!
        val (firstOp, first) = findOperations(rootJob.substringBefore(" "))
        val (secondOp, second) = findOperations(rootJob.substringAfterLast(" "))

        return firstOp.ifEmpty { secondOp }.reversed().map { it.split(" ") }.fold(first ?: second!!) { acc, (f, s) ->
            when (f in operations.keys) {
                true -> leftReversedOperations[f]!!(acc, s.toLong())
                false -> rightReversedOperations[s]!!(acc, f.toLong())
            }
        }
    }

    private fun findOperations(monkey: String): Pair<List<String>, Long?> {
        if (monkey == "humn") return emptyList<String>() to null

        val job = monkeys[monkey]!!
        return when (val number = job.toLongOrNull()) {
            null -> {
                val (firstOp, first) = findOperations(job.substringBefore(" "))
                val (secondOp, second) = findOperations(job.substringAfterLast(" "))
                val op = job.substringAfter(" ").substringBefore(" ")
                when {
                    first != null && second != null -> emptyList<String>() to operations[op]!!(first, second)
                    first == null && second != null -> firstOp + "$op $second" to null
                    first != null && second == null -> secondOp + "$first $op" to null
                    else -> error("Unsupported state")
                }
            }
            else -> emptyList<String>() to number
        }
    }
}