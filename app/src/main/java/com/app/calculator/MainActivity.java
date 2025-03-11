package com.app.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private StringBuilder inputBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        inputBuilder = new StringBuilder();
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        inputBuilder.append(button.getText().toString());
        resultText.setText(inputBuilder.toString());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        inputBuilder.append(" ").append(button.getText().toString()).append(" ");
        resultText.setText(inputBuilder.toString());
    }

    public void onClearClick(View view) {
        inputBuilder.setLength(0);
        resultText.setText("");
    }

    public void onEqualsClick(View view) {
        try {
            String expression = inputBuilder.toString();
            double result = evaluateExpression(expression);
            resultText.setText(String.valueOf(result));
            inputBuilder.setLength(0);
        } catch (Exception e) {
            resultText.setText("Error");
        }
    }

    private double evaluateExpression(String expression) {
        try {
            expression = expression.replaceAll("×", "*").replaceAll("÷", "/"); // Replace symbols
            return new net.objecthunter.exp4j.ExpressionBuilder(expression).build().evaluate();
        } catch (Exception e) {
            return Double.NaN; // Return NaN for errors
        }
    }

}

