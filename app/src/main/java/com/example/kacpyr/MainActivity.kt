package com.example.kacpyr

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.kacpyr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText = binding.editText

        val numberButtons = listOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9
        )

        val operationButtons = listOf(
            binding.button11, // Dodawanie
            binding.button12, // Odejmowanie
            binding.button13, // Mnożenie
            binding.button14  // Dzielenie
        )

        val equalsButton = binding.buttonEquals
        val clearButton = binding.buttonClear

        numberButtons.forEach { button ->
            button.setOnClickListener(this)
        }

        operationButtons.forEach { button ->
            button.setOnClickListener(this)
        }

        equalsButton.setOnClickListener {
            calculateResult()
        }

        clearButton.setOnClickListener {
            clearInput()
        }
    }

    override fun onClick(view: View?) {
        if (view is Button) {
            val buttonText = view.text.toString()
            when (buttonText) {
                "+", "-", "*", "/" -> {
                    if (editText.text.isNotEmpty() && !editText.text.last().isWhitespace()) {
                        editText.append(" $buttonText ")
                    }
                }
                else -> editText.append(buttonText)
            }
        }
    }

    private fun calculateResult() {
        val input = editText.text.toString()
        if (input.isNotEmpty()) {
            try {
                val result = evaluateExpression(input)
                editText.setText(result.toString())
            } catch (e: Exception) {
                editText.setText("Error")
            }
        }
    }

    private fun evaluateExpression(input: String): Double {
        val parts = input.trim().split("\\s+".toRegex())
        if (parts.size < 3 || parts.size % 2 == 0) {
            throw IllegalArgumentException("Incomplete or incorrect expression")
        }

        var result = parts[0].toDoubleOrNull() ?: throw IllegalArgumentException("Invalid number format")

        for (i in 1 until parts.size step 2) {
            val operator = parts[i]
            val operand = parts[i + 1].toDoubleOrNull() ?: throw IllegalArgumentException("Invalid number format")

            result = when (operator) {
                "+" -> result + operand
                "-" -> result - operand
                "*" -> result * operand
                "/" -> result / operand
                else -> throw IllegalArgumentException("Invalid operator")
            }
        }

        return result
    }

    private fun clearInput() {
        editText.text.clear()
    }
}
