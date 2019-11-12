package com.example.myapplication.forTests.ex1

import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StringReverserTest {

    private val SUT =  StringReverser()

    //<unitOfwork_stateUnderOftest_expectedResult>

    @Test
    fun reverse_emptyString_emptyStrngReturned() {
        val result = SUT.reverse("")
        assertThat(result, `is`(""))
    }

    @Test
    fun reverse_longString_reversedStringReturned() {
        val res = SUT.reverse("kazak")
        assertThat(res, `is`("kazak"))
    }

    @Test
    fun reverse_singleString_sameStringReturned() {
        val res = SUT.reverse("n")
        assertThat(res, `is`("n"))
    }

}