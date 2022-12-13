package com.clouddjr.advent2022

class Day13(input: String) {
    private val pairs = input.split("\n\n")
        .map { it.split("\n") }
        .map { (left, right) -> Packet.from(left) to Packet.from(right) }

    fun solvePart1() = pairs.mapIndexed { i, (left, right) -> if (left compareTo right < 0) i + 1 else 0 }.sum()

    fun solvePart2(): Int {
        val first = Packet.from("[[2]]")
        val second = Packet.from("[[6]]")
        val sorted = (pairs.flatMap { it.toList() } + listOf(first, second)).sortedWith(Packet::compareTo)
        return (sorted.indexOf(first) + 1) * (sorted.indexOf(second) + 1)
    }

    private sealed class Value {
        abstract infix fun compareTo(other: Value): Int

        companion object {
            fun from(definition: String): Value {
                return when (val value = definition.toIntOrNull()) {
                    null -> Packet.from(definition)
                    else -> Number(value)
                }
            }
        }
    }

    private data class Number(val value: Int) : Value() {
        override infix fun compareTo(other: Value): Int {
            return when (other) {
                is Number -> when {
                    value < other.value -> -1
                    value > other.value -> 1
                    else -> 0
                }
                is Packet -> Packet(listOf(this)) compareTo other
            }
        }
    }

    private data class Packet(val children: List<Value>) : Value() {
        override infix fun compareTo(other: Value): Int {
            return when (other) {
                is Number -> this compareTo Packet(listOf(other))
                is Packet -> {
                    children.zip(other.children)
                        .map { it.first compareTo it.second }
                        .filterNot { it == 0 }
                        .firstOrNull() ?: (children.size compareTo other.children.size)
                }
            }
        }

        companion object {
            fun from(definition: String): Packet {
                val inside = definition.drop(1).dropLast(1)
                if (inside.isEmpty()) return Packet(emptyList())

                val children = buildList {
                    var current = ""
                    var brackets = 0
                    for (c in inside) {
                        if (c == '[') brackets++
                        if (c == ']') brackets--
                        if (c == ',' && brackets == 0) {
                            add(Value.from(current))
                            current = ""
                            continue
                        }
                        current += c
                    }
                    add(Value.from(current))
                }

                return Packet(children)
            }
        }
    }
}