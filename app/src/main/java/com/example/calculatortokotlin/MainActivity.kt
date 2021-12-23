package com.example.calculatortokotlin

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.calculatortokotlin.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.calculatortokotlin.SettingsActivity
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    var valueButton1 = "1"
    var valueButton2 = "2"
    var valueButton3 = "3"
    var valueButton4 = "4"
    var valueButton5 = "5"
    var valueButton6 = "6"
    var valueButton7 = "7"
    var valueButton8 = "8"
    var valueButton9 = "9"
    var valueButton0 = "0"
    var valueButtonPoint = "."
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var button5: Button? = null
    var button6: Button? = null
    var button7: Button? = null
    var button8: Button? = null
    var button9: Button? = null
    var button0: Button? = null
    var buttonAdd: Button? = null
    var buttonSub: Button? = null
    var buttonMul: Button? = null
    var buttonDiv: Button? = null
    var buttonPoint: Button? = null
    var buttonSettings: Button? = null
    var currentValue = ""
    var currentAction = 0 // 1 - сложение; 2 - вычитание; 3 - умножение; 4 - деление;
    var arg1: Double? = null
    var arg2: Double? = null
    var result: Double? = null
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
        buttonSettings!!.setOnClickListener { v: View? ->
            val runSettings = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(runSettings)
        }
    }

    private fun initButtonDigitClickListener(but: Button?, valueButton: String) {
        but!!.setOnClickListener { view: View? ->
            setTextButton(currentValue + valueButton)
            currentValue = currentValue + valueButton
        }
    }

    private fun initButtonActionClickListener(but: Button?, currentAct: Int) {
        but!!.setOnClickListener { view: View? ->
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
        buttonResult.setOnClickListener { view: View? ->
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
                val res = java.lang.Double.toString(result!!)
                setTextButton(res)
            }
        }
    }
}