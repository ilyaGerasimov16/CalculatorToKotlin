package com.example.calculatortokotlin

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    private val valueButton1 = "1"
    private val valueButton2 = "2"
    private val valueButton3 = "3"
    private val valueButton4 = "4"
    private val valueButton5 = "5"
    private val valueButton6 = "6"
    private val valueButton7 = "7"
    private val valueButton8 = "8"
    private val valueButton9 = "9"
    private val valueButton0 = "0"
    private val valueButtonPoint = "."
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null
    private var button0: Button? = null
    private var buttonAdd: Button? = null
    private var buttonSub: Button? = null
    private var buttonMul: Button? = null
    private var buttonDiv: Button? = null
    private var buttonPoint: Button? = null
    private var buttonSettings: Button? = null
    private var currentValue = ""
    private var currentAction = 0 // 1 - сложение; 2 - вычитание; 3 - умножение; 4 - деление;
    private var arg1: Double? = null
    private var arg2: Double? = null
    private var result: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initButtons()
        initClickListeners()
    }

    private fun initButtons() {
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button0 = findViewById(R.id.button0)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSub = findViewById(R.id.buttonSub)
        buttonMul = findViewById(R.id.buttonMul)
        buttonDiv = findViewById(R.id.buttonDiv)
        buttonPoint = findViewById(R.id.buttonPoint)
        buttonSettings = findViewById(R.id.button_settings)
    }

    private fun initViews() {
        textView = findViewById(R.id.textView1)
    }

    private fun setTextButton(value: String) {
        textView!!.text = String.format(Locale.getDefault(), "%s", value)
    }

    private fun initClickListeners() {
        initButtonDigitClickListener(button0, valueButton0)
        initButtonDigitClickListener(button1, valueButton1)
        initButtonDigitClickListener(button2, valueButton2)
        initButtonDigitClickListener(button3, valueButton3)
        initButtonDigitClickListener(button4, valueButton4)
        initButtonDigitClickListener(button5, valueButton5)
        initButtonDigitClickListener(button6, valueButton6)
        initButtonDigitClickListener(button7, valueButton7)
        initButtonDigitClickListener(button8, valueButton8)
        initButtonDigitClickListener(button9, valueButton9)
        initButtonDigitClickListener(buttonPoint, valueButtonPoint)
        initButtonActionClickListener(buttonAdd, 1)
        initButtonActionClickListener(buttonSub, 2)
        initButtonActionClickListener(buttonMul, 3)
        initButtonActionClickListener(buttonDiv, 4)
        initButtonResultClickListener()
        initButtonSettingsClickListener()
    }

    private fun initButtonSettingsClickListener() {
        buttonSettings!!.setOnClickListener {
            val runSettings = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(runSettings)
        }
    }

    private fun initButtonDigitClickListener(but: Button?, valueButton: String) {
        but!!.setOnClickListener {
            setTextButton(currentValue + valueButton)
            currentValue += valueButton
        }
    }

    private fun initButtonActionClickListener(but: Button?, currentAct: Int) {
        but!!.setOnClickListener {
            try {
                if (currentValue == "" && but == buttonSub) {
                    currentValue = "-"
                } else {
                    currentAction = currentAct
                    arg1 = currentValue.toDouble()
                    currentValue = ""
                }
                setTextButton(currentValue)
            } catch (e: Exception) {
                currentValue = ""
            }
        }
    }

    private fun initButtonResultClickListener() {
        val buttonResult = findViewById<Button>(R.id.buttonResult)
        buttonResult.setOnClickListener {
            if (currentValue == "") {
                setTextButton("0")
            } else {
                arg2 = currentValue.toDouble()
                currentValue = ""
                result = when (currentAction) {
                    1 -> arg1!! + arg2!!
                    2 -> arg1!! - arg2!!
                    3 -> arg1!! * arg2!!
                    4 -> arg1!! / arg2!!
                    else -> 0.0
                }
                val res = (result).toString()
                setTextButton(res)
            }
        }
    }
}