package com.raysono.hfu.myapplication

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day1 {

    // https://adventofcode.com/2021/day/1

    @Test
    fun `solution 1`() {
        val input: List<Int> = File("src/test/Day1Input.txt").readLines().map { it.toInt() }

        var count = 0
        for (index in 0 until input.size) {
            if (index == 0) continue
            val current = input[index]
            val previous = input[index - 1]
            if (current > previous) {
                count += 1
            }
        }

        Assert.assertEquals(count, 7)
    }

    @Test
    fun `solution 2`() {
        val input: List<Int> = File("src/test/Day1Input.txt").readLines().map { it.toInt() }

        var count = 0
        for ((index, current) in input.withIndex()) {
            if (index == 0) continue
            val previous = input[index - 1]
            if (current > previous) {
                count += 1
            }
        }

        Assert.assertEquals(count, 7)
    }

    @Test
    fun `solution 3`() {
        val input: List<Int> = File("src/test/Day1Input.txt").readLines().map { it.toInt() }

        var count = 0

        input.forEachIndexed { index, current ->
            if (index != 0) {
                val previous = input[index - 1]
                if (current > previous) {
                    count += 1
                }
            }
        }

        Assert.assertEquals(count, 7)
    }

    @Test
    fun `solution 4`() {
        val input: List<Int> = File("src/test/Day1Input.txt").readLines().map { it.toInt() }

        val pairs: List<Pair<Int, Int>> = input.mapIndexedNotNull { index, current ->
            if (index > 0) {
                Pair(input[index - 1], current)
            } else {
                null
            }
        }

        val count: Int = pairs.count { (first, second) -> first < second }

        Assert.assertEquals(count, 7)
    }

    @Test
    fun `solution 5`() {
        val count = File("src/test/Day1Input.txt")
            .readLines()
            .map { it.toInt() }
            .zipWithNext()
            .count { (first, second) -> first < second }

        Assert.assertEquals(count, 7)
    }
}
