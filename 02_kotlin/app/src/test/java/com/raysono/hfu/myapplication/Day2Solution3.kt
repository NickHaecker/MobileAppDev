package com.raysono.hfu.myapplication

import java.io.File
import org.junit.Assert
import org.junit.Test

class Day2Solution3 {

    // https://adventofcode.com/2021/day/2

    @Test
    fun `solution 1`() {
        val input = File("src/test/Day2Input.txt").readLines()
        val commands = input.mapNotNull { line ->
            val parts: List<String> = line.split(" ")
            val delta = parts[1].toInt()
            when (parts.first()) {
                "forward" -> Command.Forward(delta)
                "up" -> Command.Up(delta)
                "down" -> Command.Down(delta)
                else -> null
            }
        }

        var position = Position(0, 0)
        commands.forEach { command ->
            position = when (command) {
                is Command.Forward -> position.copy(horizontal = position.horizontal + command.delta)
                is Command.Up -> position.copy(depth = position.depth - command.delta)
                is Command.Down -> position.copy(depth = position.depth + command.delta)
            }
        }

        val result = position.horizontal * position.depth
        Assert.assertEquals(result, 150)
    }

    private sealed class Command {
        abstract val delta: Int
        class Forward(override val delta: Int) : Command()
        class Up(override val delta: Int) : Command()
        class Down(override val delta: Int) : Command()
    }

    private data class Position(val horizontal: Int, val depth: Int)
}

