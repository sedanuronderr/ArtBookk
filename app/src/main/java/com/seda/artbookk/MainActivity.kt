package com.seda.artbookk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      /*  val tax = Tax()
        val income = tax.calculateIncome(100.0,0.1)
          val nettax = tax.calculateTax(100.0,0.1)
        println(nettax)
        println(income)*/

    }
}