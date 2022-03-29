package com.raysono.hfu.myapplication

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day2Solution5 {

    // https://adventofcode.com/2021/day/2

    @Test
    fun `solution 1`() {
        val input = File("src/test/Day2Input.txt").readLines()

        val position = input.getCommands().fold(Position(0, 0)) { result, command ->
            when (command) {
                is Command.Forward -> result.copy(horizontal = result.horizontal + command.delta)
                is Command.Up -> result.copy(depth = result.depth - command.delta)
                is Command.Down -> result.copy(depth = result.depth + command.delta)
            }
        }

        val result = position.total
        Assert.assertEquals(result, 150)
    }

    private sealed class Command {
        abstract val delta: Int
        class Forward(override val delta: Int) : Command()
        class Up(override val delta: Int) : Command()
        class Down(override val delta: Int) : Command()
    }

    private data class Position(val horizontal: Int, val depth: Int)

    private val Position.total get() = horizontal * depth

    private fun List<String>.getCommands() = mapNotNull { line ->
        val parts: List<String> = line.split(" ")
        val delta = parts[1].toInt()
        when (parts.first()) {
            "forward" -> Command.Forward(delta)
            "up" -> Command.Up(delta)
            "down" -> Command.Down(delta)
            else -> null
        }
    }
}
