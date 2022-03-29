package com.example.test


import org.junit.Assert.*
import org.junit.Test
import java.io.File


class Day1 {


    @Test
    fun `solution 1`() {
        val ints: List<Int> = File("src/test/java/Day1Input.txt").readLines().map { it.toInt() }



        var count = 0;

        ints.forEachIndexed { index, i ->
            if(index != 0){
                val prev = ints[index-1]
                if(i > prev){
                    count += 1;
                }
            }
        }

        assertEquals(count,7)

    }

}