package com.example.scientificcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1;
    private EditText etNum2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvResult = findViewById(R.id.tvResult);

        // Bind buttons to operations
        setupButton(R.id.btnAdd, "+");
        setupButton(R.id.btnSub, "-");
        setupButton(R.id.btnMul, "*");
        setupButton(R.id.btnDiv, "/");
        setupButton(R.id.btnSin, "sin");
        setupButton(R.id.btnCos, "cos");
        setupButton(R.id.btnTan, "tan");
        setupButton(R.id.btnSqrt, "sqrt");
        setupButton(R.id.btnLog, "log");
    }

    private void setupButton(int resId, final String operation) {
        Button button = findViewById(resId);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performCalculation(operation);
                }
            });
        }
    }

    private void performCalculation(String operation) {
        String num1Str = etNum1.getText().toString().trim();
        String num2Str = etNum2.getText().toString().trim();

        if (num1Str.isEmpty()) {
            Toast.makeText(this, "Please enter the first number", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1;
        double num2 = 0;

        try {
            num1 = Double.parseDouble(num1Str);
        } catch (NumberFormatException e) {
            tvResult.setText("Error: Invalid Number 1");
            return;
        }

        // Check if operation requires a second number (basic operations)
        boolean isBinaryOperation = operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/");
        
        if (isBinaryOperation) {
            if (num2Str.isEmpty()) {
                Toast.makeText(this, "Please enter the second number", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                num2 = Double.parseDouble(num2Str);
            } catch (NumberFormatException e) {
                tvResult.setText("Error: Invalid Number 2");
                return;
            }
        }

        String result = calculateScientific(num1, num2, operation);
        tvResult.setText("Result: " + result);
    }

    // Ported scientific calculator logic from Python
    private String calculateScientific(double num1, double num2, String operation) {
        switch (operation) {
            // 1. Basic Operations
            case "+":
                return String.valueOf(num1 + num2);
            case "-":
                return String.valueOf(num1 - num2);
            case "*":
                return String.valueOf(num1 * num2);
            case "/":
                if (num2 != 0) {
                    return String.valueOf(num1 / num2);
                } else {
                    return "Error: Division by Zero";
                }
                
            // 2. Scientific Operations (Radians)
            case "sin":
                return String.valueOf(Math.sin(num1));
            case "cos":
                return String.valueOf(Math.cos(num1));
            case "tan":
                return String.valueOf(Math.tan(num1));
            case "sqrt":
                if (num1 >= 0) {
                    return String.valueOf(Math.sqrt(num1));
                } else {
                    return "Error: Invalid Input";
                }
            case "log":
                if (num1 > 0) {
                    return String.valueOf(Math.log10(num1));
                } else {
                    return "Error: Invalid Input";
                }
            default:
                return "Unknown Operation";
        }
    }
}