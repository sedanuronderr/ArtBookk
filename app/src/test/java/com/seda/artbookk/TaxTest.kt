package com.seda.artbookk

import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class TaxTest {
    private lateinit var tax:Tax
@Before
fun setup(){
    tax = Tax()

}
    @After
    fun teardown(){

    }

    @Test
    fun calculateTax() {
        val nettax = tax.calculateTax(100.0,0.1)
        //bu buna eşit mi diye kontrol eder
 assertEquals("sonuc",nettax,10.0,0.0001)

    }

    @Test
    fun calculateIncome() {

        val income = tax.calculateIncome(100.0,0.1)
        assertEquals("incode",income,90.0,0.0001)
    }
}