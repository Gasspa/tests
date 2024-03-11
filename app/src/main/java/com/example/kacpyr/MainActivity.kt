package com.example.kacpyr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kacpyr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText = binding.editText

        val numberButtons = listOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.button0
        )

        val operationButtons = listOf(
            binding.button11,  // +
            binding.button12   // -
        )

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                appendToEditText((index + 1).toString())
            }
        }

        operationButtons.forEach { button ->
            button.setOnClickListener {
                appendToEditText(button.text.toString())
            }
        }
    }

    private fun appendToEditText(text: String) {
        val currentText = editText.text.toString()
        editText.setText("$currentText$text")
    }
}
