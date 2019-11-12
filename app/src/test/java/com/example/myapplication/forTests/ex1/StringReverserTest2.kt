package com.example.myapplication.forTests.ex1

import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StringReverserTest2 {

    private var SUT = StringReverser()

    @Before
    fun setUp() {

    }

    @Test
    fun reverse_longString_reversedLongStringReturned() {
        val res = SUT.reverse("kazakh")
        assertThat(res, `is`("hkazak"))
    }

    @Test
    fun reverse_singleCharacter_sameStringReturned() {
        val res = SUT.reverse("a")
        assertThat(res, `is`("a"))
    }

}