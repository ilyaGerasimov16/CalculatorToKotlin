package com.example.calculatortokotlin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    String valueButton1 ="1";
    String valueButton2 ="2";
    String valueButton3 ="3";
    String valueButton4 ="4";
    String valueButton5 ="5";
    String valueButton6 ="6";
    String valueButton7 ="7";
    String valueButton8 ="8";
    String valueButton9 ="9";
    String valueButton0 ="0";
    String valueButtonPoint =".";

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttonAdd;
    Button buttonSub;
    Button buttonMul;
    Button buttonDiv;
    Button buttonPoint;
    Button buttonSettings;


    String currentValue = "";
    int currentAction = 0; // 1 - сложение; 2 - вычитание; 3 - умножение; 4 - деление;
    Double arg1;
    Double arg2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initButtons();
        initClickListeners();

    }

    private void initButtons() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonSettings = findViewById(R.id.button_settings);
    }

    private void initViews() {
        textView = findViewById(R.id.textView1);
    }

    private void setTextButton( String value) {
        textView.setText(String.format(Locale.getDefault(), "%s",value) );
    }


    private void initClickListeners(){

        initButtonDigitClickListener(this.button0, valueButton0);
        initButtonDigitClickListener(button1, valueButton1);
        initButtonDigitClickListener(button2, valueButton2);
        initButtonDigitClickListener(button3, valueButton3);
        initButtonDigitClickListener(button4, valueButton4);
        initButtonDigitClickListener(button5, valueButton5);
        initButtonDigitClickListener(button6, valueButton6);
        initButtonDigitClickListener(button7, valueButton7);
        initButtonDigitClickListener(button8, valueButton8);
        initButtonDigitClickListener(button9, valueButton9);
        initButtonDigitClickListener(buttonPoint, valueButtonPoint);


        initButtonActionClickListener(buttonAdd, 1);
        initButtonActionClickListener(buttonSub, 2);
        initButtonActionClickListener(buttonMul, 3);
        initButtonActionClickListener(buttonDiv, 4);

        initButtonResultClickListener();

        initButtonSettingsClickListener();
    }

    private void initButtonSettingsClickListener() {
        buttonSettings.setOnClickListener(v -> {
            Intent runSettings = new Intent (MainActivity.this, SettingsActivity.class);
            startActivity(runSettings);
        });
    }

    private void initButtonDigitClickListener(Button but, String valueButton) {
        but.setOnClickListener(view -> {
            setTextButton(currentValue + valueButton);
            currentValue = currentValue + valueButton;
        });
    }

    private void initButtonActionClickListener(Button but, int currentAct) {

        but.setOnClickListener(view -> {
            try {
                if (currentValue.equals("")&& but.equals(buttonSub)) {
                    currentValue = "-";
                } else {
                    currentAction = currentAct;
                    arg1 = Double.parseDouble(currentValue);
                    currentValue = "";
                }
                setTextButton(currentValue);
            }
            catch (Exception e) {
                currentValue = "";
            }

        });
    }

    private void initButtonResultClickListener() {
        Button buttonResult = findViewById(R.id.buttonResult);

        buttonResult.setOnClickListener(view -> {
            if (currentValue.equals("")) {
                setTextButton("0");
            } else {
                arg2 = Double.parseDouble(currentValue);
                currentValue = "";

                switch (currentAction) {
                    case 1: result = arg1 + arg2;
                        break;
                    case 2: result = arg1 - arg2;
                        break;
                    case 3: result = arg1 * arg2;
                        break;
                    case 4: result = arg1 / arg2;
                        break;
                    default: result = 0.0;
                        break;
                }
                String res = Double.toString(result);
                setTextButton(res);
            }
        });
    }
}