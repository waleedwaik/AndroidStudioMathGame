package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var score = 0
    private var questionCount = 0
    private lateinit var number1: Number1
    private lateinit var operation: Operation
    private lateinit var number2: Number2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1 = Number1()
        operation = Operation()
        number2 = Number2()

        updateTV()
        checkButton.setOnClickListener { checkAnswer() }
    }

    private fun updateTV() {
        val n1 = number1.chooseNumber((0..9).random())
        val op = operation.chooseOperation((0..9).random())
        val n2 = number2.chooseNumber((0..9).random())

        n1textV.text = n1.toString()
        optextV.text = op.toString()
        n2textV.text = n2.toString()
    }

    private fun checkAnswer() {
        val n1 = number1.chooseNumber((0..9).random())
        val op = operation.chooseOperation((0..9).random())
        val n2 = number2.chooseNumber((0..9).random())

        val answer = when (op) {
            '+' -> n1 + n2
            '-' -> n1 - n2
            '*' -> n1 * n2
            else -> n1 / n2
        }

        val ans = ansEditT.text.toString().toIntOrNull()

        if (ans != null) {
            if (ans == answer) {
                score++
                scoretextV.text = score.toString()
                resultTextView.text = "Correct! Next"
            } else {
                resultTextView.text = "Wrong! Next"
            }

            questionCount++
            if (questionCount == 10) {
                endGame()
            } else {
                updateTV()
                ansEditT.text.clear()
            }
        }
    }

    private fun endGame() {
        questionCount = 0
        Toast.makeText(this, "You scored $score out of 10!", Toast.LENGTH_LONG).show()
        score = 0
        scoretextV.text = "0"
        updateTV()
    }
}
class Number1 {
    private val numbers = intArrayOf(3, 8, 5, 1, 4, 9, 0, 6, 7, 2)

    fun chooseNumber(i: Int): Int {
        return numbers[i]
    }
}

class Operation {
    private val operations = charArrayOf('+', '-', '*', '/')

    fun chooseOperation(i: Int): Char {
        return operations[i % 4]
    }
}

class Number2 {
    private val numbers = intArrayOf(3, 8, 5, 1, 4, 9, 0, 6, 7, 2)

    fun chooseNumber(i: Int): Int {
        return numbers[10 - (i + 1)]
    }
}



