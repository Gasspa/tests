package com.example.kacpyr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kacpyr.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pobierz referencje do przycisków z pliku XML
        val button1 = binding.button1
        val button2 = binding.button2
        val button3 = binding.button3

        // Ustaw obsługę zdarzeń dla każdego przycisku
        button1.setOnClickListener {
            showToast("Naciśnięto przycisk 1")
        }

        button2.setOnClickListener {
            showToast("Naciśnięto przycisk 2")
        }

        button3.setOnClickListener {
            showToast("Naciśnięto przycisk 3")
        }
    }

    // Prosta funkcja do wyświetlania komunikatu Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
