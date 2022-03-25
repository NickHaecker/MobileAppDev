package com.raysono.hfu.myapplication

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day2Solution1 {

    // https://adventofcode.com/2021/day/2

    @Test
    fun `solution 1`() {
        val input = File("src/test/Day2Input.txt").readLines()
        val commands = input.map { line ->
            val parts: List<String> = line.split(" ")
            Command(parts[0], parts[1].toInt())
        }

        var horizontal = 0
        var depth = 0
        commands.forEach { command ->
            when (command.name) {
                "forward" -> horizontal += command.delta
                "up" -> depth -= command.delta
                "down" -> depth += command.delta
            }
        }

        val result = horizontal * depth
        Assert.assertEquals(result, 150)
    }

    private class Command(val name: String, val delta: Int)
}
