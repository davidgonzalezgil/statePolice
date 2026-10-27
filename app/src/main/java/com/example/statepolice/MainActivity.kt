package com.example.statepolice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_COUNT = "KEY_COUNT"
    }

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var textViewCounter: TextView
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        textViewCounter = findViewById(R.id.textViewCounter)

        button.setOnClickListener {
            count++
            textViewCounter.text = count.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_LONG).show()
        outState.putInt(KEY_COUNT, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_LONG).show()

        count = savedInstanceState.getInt(KEY_COUNT, 0)
        textViewCounter.text = count.toString()

        val textActual = editText.text.toString()
        editText.setText(textActual.uppercase())
        editText.setSelection(editText.text.length)
    }
}