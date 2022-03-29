package com.example.test

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day2 {

    @Test
    fun `solution 1`() {
        val input  = File("src/test/java/Day2Input.txt").readLines()

        val input2:List<Command> = input.map {line ->
            val parts: List<String> = line.split(" ");
            Command(parts.first(),parts[1].toInt());
        }
    }


}

class Command(val name:String, val delta: Int)