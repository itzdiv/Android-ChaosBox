package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {
    Button b6;

    private EditText number1EditText, number2EditText, resultEditText;
    private Button addButton, subtractButton, multiplyButton, divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calculator);
        b6=(Button)findViewById(R.id.back_button);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Calculator.this,Signout.class);
                startActivity(k);
                finish();
            }
        });


        // Initialize UI elements
        number1EditText = findViewById(R.id.number1_editText);
        number2EditText = findViewById(R.id.number2_editText);
        resultEditText = findViewById(R.id.result_editText);
        addButton = findViewById(R.id.add_button);
        subtractButton = findViewById(R.id.subtract_button);
        multiplyButton = findViewById(R.id.multiply_button);
        divideButton = findViewById(R.id.divide_button);

        // Set listeners for buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });
    }

    private void calculate(String operator) {
        // Get the two input numbers
        String num1String = number1EditText.getText().toString().trim();
        String num2String = number2EditText.getText().toString().trim();

        if (TextUtils.isEmpty(num1String)) {
            number1EditText.setError("Please enter a number");
            number1EditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(num2String)) {
            number2EditText.setError("Please enter a number");
            number2EditText.requestFocus();
            return;
        }

        double num1 = Double.parseDouble(num1String);
        double num2 = Double.parseDouble(num2String);

        // Calculate the result based on the operator
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }

        // Display the result
        resultEditText.setText(String.format("%.2f", result));
    }

}
