package com.raysono.hfu.myapplication

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day2Solution2 {

    // https://adventofcode.com/2021/day/2

    @Test
    fun `solution 1`() {
        val input = File("src/test/Day2Input.txt").readLines()
        val commands = input.map { line ->
            val parts: List<String> = line.split(" ")
            Command(parts[0], parts[1].toInt())
        }

        var position = Position(0, 0)
        commands.forEach { command ->
            when (command.name) {
                "forward" -> position = position.copy(horizontal = position.horizontal + command.delta)
                "up" -> position = position.copy(depth = position.depth - command.delta)
                "down" -> position = position.copy(depth = position.depth + command.delta)
            }
        }

        val result = position.horizontal * position.depth
        Assert.assertEquals(result, 150)
    }

    private class Command(val name: String, val delta: Int)
    private data class Position(val horizontal: Int, val depth: Int)
}

