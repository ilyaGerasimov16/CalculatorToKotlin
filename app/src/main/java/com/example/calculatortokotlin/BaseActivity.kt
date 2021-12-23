package com.example.calculatortokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getAppTheme(R.style.MyCoolStyle))
    }

    private fun getAppTheme(codeStyle: Int): Int {
        return codeStyleToStyleId(getCodeStyle(codeStyle))
    }

    private fun codeStyleToStyleId(codeStyle: Int): Int {
        return when (codeStyle) {
            AppThemeCodeStyle -> R.style.Theme_Lesson2
            AppThemeLightCodeStyle -> R.style.AppThemeLight
            AppThemeDarkCodeStyle -> R.style.AppThemeDark
            else -> R.style.MyCoolStyle
        }
    }

    protected fun getCodeStyle(codeStyle: Int): Int {
        val sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE)
        return sharedPref.getInt(AppTheme, codeStyle)
    }

    protected fun setAppTheme(codeStyle: Int) {
        val sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(AppTheme, codeStyle)
        editor.apply()
    }


    companion object {
        private const val NameSharedPreference = "Calc"
        private const val AppTheme = "APP_THEME"
        const val MyCoolCodeStyle = 0
        const val AppThemeLightCodeStyle = 1
        const val AppThemeCodeStyle = 2
        const val AppThemeDarkCodeStyle = 3
    }
}