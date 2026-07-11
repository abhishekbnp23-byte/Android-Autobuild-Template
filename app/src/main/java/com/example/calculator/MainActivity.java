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

        // UI एलिमेंट्स को इनिशियलाइज करना
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvResult = findViewById(R.id.tvResult);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnSin = findViewById(R.id.btnSin);
        Button btnCos = findViewById(R.id.btnCos);
        Button btnTan = findViewById(R.id.btnTan);
        Button btnSqrt = findViewById(R.id.btnSqrt);
        Button btnLog = findViewById(R.id.btnLog);

        // बेसिक गणितीय ऑपरेशन्स के लिए श्रोता (Listeners)
        btnAdd.setOnClickListener(v -> processCalculation("+"));
        btnSub.setOnClickListener(v -> processCalculation("-"));
        btnMul.setOnClickListener(v -> processCalculation("*"));
        btnDiv.setOnClickListener(v -> processCalculation("/"));

        // एडवांस साइंटिफिक ऑपरेशन्स के लिए श्रोता (Listeners)
        btnSin.setOnClickListener(v -> processCalculation("sin"));
        btnCos.setOnClickListener(v -> processCalculation("cos"));
        btnTan.setOnClickListener(v -> processCalculation("tan"));
        btnSqrt.setOnClickListener(v -> processCalculation("sqrt"));
        btnLog.setOnClickListener(v -> processCalculation("log"));
    }

    private void processCalculation(String operation) {
        String num1Str = etNum1.getText().toString().trim();
        String num2Str = etNum2.getText().toString().trim();

        double num1 = 0;
        double num2 = 0;

        // इनपुट वैलिडेशन
        if (num1Str.isEmpty()) {
            Toast.makeText(this, "Please enter at least First Number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            num1 = Double.parseDouble(num1Str);
            if (!num2Str.isEmpty()) {
                num2 = Double.parseDouble(num2Str);
            }
        } catch (NumberFormatException e) {
            tvResult.setText("Error: Invalid Input Format");
            return;
        }

        // गणना लॉजिक को कॉल करना
        String result = calculateScientific(num1, num2, operation);
        tvResult.setText("Result: " + result);
    }

    // Python लॉजिक का जावा रूपांतरण
    private String calculateScientific(double num1, double num2, String operation) {
        switch (operation) {
            // 1. बेसिक गणितीय ऑपरेशन्स (Basic Operations)
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

            // 2. एडवांस साइंटिफिक ऑपरेशन्स (Scientific Operations)
            // ध्यान दें: इनपुट्स को रेडियन में मानकर गणना की जा रही है
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