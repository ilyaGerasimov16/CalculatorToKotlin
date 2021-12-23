package com.example.calculatortokotlin


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import com.google.android.material.radiobutton.MaterialRadioButton

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acrivity_settings)
        val btnReturn = findViewById<Button>(R.id.button_return)
        btnReturn.setOnClickListener { v: View? -> finish() }
        initThemeChooser()
    }

    private fun initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMyCoolStyle), MyCoolCodeStyle)
        initRadioButton(findViewById(R.id.radioButtonMaterialDark), AppThemeDarkCodeStyle)
        initRadioButton(findViewById(R.id.radioButtonMaterialLight), AppThemeLightCodeStyle)
        initRadioButton(findViewById(R.id.radioButtonMaterialLightDarkAction), AppThemeCodeStyle)
        val rg = findViewById<RadioGroup>(R.id.radioButtons)
        (rg.getChildAt(getCodeStyle(MyCoolCodeStyle)) as MaterialRadioButton).isChecked = true
    }

    private fun initRadioButton(button: View, codeStyle: Int) {
        button.setOnClickListener {
            setAppTheme(codeStyle)
            recreate()
        }
    }
}