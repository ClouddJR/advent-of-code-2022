package com.clouddjr.advent2022

class Day16(input: List<String>) {
    private val valves = input.map(Valve::from).associateBy(Valve::name)
    private val usefulValves = valves.filter { it.value.rate > 0 }

    private val distances = computeDistances()

    fun solvePart1() = traverse(minutes = 30)

    fun solvePart2() = traverse(minutes = 26, elephantGoesNext = true)

    private fun traverse(
        minutes: Int,
        current: Valve = valves.getValue("AA"),
        remaining: Set<Valve> = usefulValves.values.toSet(),
        cache: MutableMap<State, Int> = mutableMapOf(),
        elephantGoesNext: Boolean = false
    ): Int {
        val currentScore = minutes * current.rate
        val currentState = State(current.name, minutes, remaining)

        return currentScore + cache.getOrPut(currentState) {
            val maxCurrent = remaining
                .filter { next -> distances[current.name]!![next.name]!! < minutes }
                .takeIf { it.isNotEmpty() }
                ?.maxOf { next ->
                    val remainingMinutes = minutes - 1 - distances[current.name]!![next.name]!!
                    traverse(remainingMinutes, next, remaining - next, cache, elephantGoesNext)
                }
                ?: 0
            maxOf(maxCurrent, if (elephantGoesNext) traverse(minutes = 26, remaining = remaining) else 0)
        }
    }

    private fun computeDistances(): Map<String, Map<String, Int>> {
        return valves.keys.map { valve ->
            val distances = mutableMapOf<String, Int>().withDefault { Int.MAX_VALUE }.apply { put(valve, 0) }
            val toVisit = mutableListOf(valve)
            while (toVisit.isNotEmpty()) {
                val current = toVisit.removeFirst()
                valves[current]!!.next.forEach { neighbour ->
                    val newDistance = distances[current]!! + 1
                    if (newDistance < distances.getValue(neighbour)) {
                        distances[neighbour] = newDistance
                        toVisit.add(neighbour)
                    }
                }
            }
            distances
        }.associateBy { it.keys.first() }
    }

    private data class State(val current: String, val minutes: Int, val opened: Set<Valve>)

    private data class Valve(val name: String, val rate: Int, val next: List<String>) {
        companion object {
            fun from(line: String): Valve {
                return Valve(
                    name = line.substringAfter("Valve ").substringBefore(" "),
                    rate = line.substringAfter("rate=").substringBefore(";").toInt(),
                    next = line.substringAfter("to valve").substringAfter(" ").split(", ")
                )
            }
        }
    }
}