package com.clouddjr.advent2022

import java.util.PriorityQueue
import kotlin.math.ceil
import kotlin.math.max

class Day19(input: List<String>) {
    private val blueprints = input.map(Blueprint::from)

    fun solvePart1() = blueprints.sumOf { it.id * Factory(it, minutes = 24).findBest() }

    fun solvePart2() = blueprints.take(3).map { Factory(it, minutes = 32).findBest() }.reduce(Int::times)

    private class Factory(val blueprint: Blueprint, val minutes: Int) {
        fun findBest(): Int {
            var currentBest = 0
            val queue = PriorityQueue<State>().apply { add(State(minutes = minutes)) }

            while (queue.isNotEmpty()) {
                val state = queue.poll()
                if (state.canOutproduce(currentBest)) {
                    queue.addAll(state.nextSensibleStates())
                }
                currentBest = max(currentBest, state.geode + state.geodeRobots * (state.minutes - 1))
            }

            return currentBest
        }

        private fun State.nextSensibleStates() = buildList {
            if (blueprint.maxOre > oreRobots) {
                add(nextWith(blueprint.oreRobot))
            }
            if (blueprint.maxClay > clayRobots) {
                add(nextWith(blueprint.clayRobot))
            }
            if (clayRobots > 0 && blueprint.maxObsidian > obsidianRobots) {
                add(nextWith(blueprint.obsidianRobot))
            }
            if (obsidianRobots > 0) {
                add(nextWith(blueprint.geodeRobot))
            }
        }.filter { it.minutes > 0 }
    }

    private data class State(
        val minutes: Int,
        val ore: Int = 1,
        val clay: Int = 0,
        val obsidian: Int = 0,
        val geode: Int = 0,
        val oreRobots: Int = 1,
        val clayRobots: Int = 0,
        val obsidianRobots: Int = 0,
        val geodeRobots: Int = 0,
    ) : Comparable<State> {
        override fun compareTo(other: State) = other.geode.compareTo(geode)

        fun nextWith(robot: Robot): State {
            val minutes = timeToBuild(robot)
            return copy(
                minutes = this.minutes - minutes,
                ore = ore + oreRobots * minutes - robot.oreCost,
                clay = clay + clayRobots * minutes - robot.clayCost,
                obsidian = obsidian + obsidianRobots * minutes - robot.obsidianCost,
                geode = geode + geodeRobots * minutes,
                oreRobots = oreRobots + robot.oreRobotsBuilt,
                clayRobots = clayRobots + robot.clayRobotsBuilt,
                obsidianRobots = obsidianRobots + robot.obsidianRobotsBuilt,
                geodeRobots = geodeRobots + robot.geodeRobotsBuilt
            )
        }

        fun canOutproduce(best: Int): Boolean {
            val potentialProduction = (0 until minutes - 1).sumOf { it + geodeRobots }
            return geode + potentialProduction > best
        }

        private fun timeToBuild(robot: Robot): Int {
            val remainingOre = (robot.oreCost - ore).coerceAtLeast(0)
            val remainingClay = (robot.clayCost - clay).coerceAtLeast(0)
            val remainingObsidian = (robot.obsidianCost - obsidian).coerceAtLeast(0)
            return maxOf(
                ceil(remainingOre / oreRobots.toFloat()).toInt(),
                ceil(remainingClay / clayRobots.toFloat()).toInt(),
                ceil(remainingObsidian / obsidianRobots.toFloat()).toInt(),
            ) + 1
        }
    }

    private data class Blueprint(
        val id: Int,
        val oreRobot: Robot,
        val clayRobot: Robot,
        val obsidianRobot: Robot,
        val geodeRobot: Robot
    ) {
        val maxOre = maxOf(oreRobot.oreCost, clayRobot.oreCost, obsidianRobot.oreCost, geodeRobot.oreCost)
        val maxClay = obsidianRobot.clayCost
        val maxObsidian = geodeRobot.obsidianCost

        companion object {
            private val pattern = """\d+""".toRegex()

            fun from(line: String): Blueprint {
                val numbers = pattern.findAll(line).map { it.value.toInt() }.toList()
                return Blueprint(
                    id = numbers[0],
                    oreRobot = Robot(oreCost = numbers[1], oreRobotsBuilt = 1),
                    clayRobot = Robot(oreCost = numbers[2], clayRobotsBuilt = 1),
                    obsidianRobot = Robot(oreCost = numbers[3], clayCost = numbers[4], obsidianRobotsBuilt = 1),
                    geodeRobot = Robot(oreCost = numbers[5], obsidianCost = numbers[6], geodeRobotsBuilt = 1)
                )
            }
        }
    }

    private data class Robot(
        val oreCost: Int = 0,
        val clayCost: Int = 0,
        val obsidianCost: Int = 0,
        val oreRobotsBuilt: Int = 0,
        val clayRobotsBuilt: Int = 0,
        val obsidianRobotsBuilt: Int = 0,
        val geodeRobotsBuilt: Int = 0,
    )
}