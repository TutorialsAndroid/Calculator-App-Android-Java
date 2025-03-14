package com.app.calculator;  // Package declaration for organizing code into namespaces

// Import necessary Android and Java libraries
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Main activity class inheriting from AppCompatActivity, providing compatibility support for older Android versions
public class MainActivity extends AppCompatActivity {

    // TextView to display results and input
    private TextView resultText;
    // StringBuilder to dynamically build and store the input expression
    private StringBuilder inputBuilder;

    // onCreate() method is called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // Call the superclass implementation
        setContentView(R.layout.activity_main);  // Set the layout for this activity

        // Initialize the TextView by finding it in the layout using its ID
        resultText = findViewById(R.id.resultText);
        // Initialize the StringBuilder to hold the user input
        inputBuilder = new StringBuilder();
    }

    // Method called when a digit button is clicked
    public void onDigitClick(View view) {
        // Get the clicked button and append its text to inputBuilder
        Button button = (Button) view;
        inputBuilder.append(button.getText().toString());
        // Update the resultText with the current input
        resultText.setText(inputBuilder.toString());
    }

    // Method called when an operator button (+, -, ×, ÷) is clicked
    public void onOperatorClick(View view) {
        // Get the clicked operator button and append it to the input with spaces for readability
        Button button = (Button) view;
        inputBuilder.append(" ").append(button.getText().toString()).append(" ");
        // Update the resultText with the current input
        resultText.setText(inputBuilder.toString());
    }

    // Method called when the clear button (C) is clicked
    public void onClearClick(View view) {
        // Clear the inputBuilder
        inputBuilder.setLength(0);
        // Clear the resultText
        resultText.setText("");
    }

    // Method called when the equals (=) button is clicked to evaluate the expression
    public void onEqualsClick(View view) {
        try {
            // Convert inputBuilder to a String representing the expression
            String expression = inputBuilder.toString();
            // Evaluate the expression and get the result
            double result = evaluateExpression(expression);
            // Display the result in resultText
            resultText.setText(String.valueOf(result));
            // Clear inputBuilder after evaluation
            inputBuilder.setLength(0);
        } catch (Exception e) {
            // Handle any exceptions by displaying "Error"
            resultText.setText("Error");
        }
    }

    // Private method to evaluate the mathematical expression using exp4j library
    private double evaluateExpression(String expression) {
        try {
            // Replace user-friendly operators with proper symbols for evaluation
            expression = expression.replaceAll("×", "*").replaceAll("÷", "/");
            // Use exp4j ExpressionBuilder to parse and evaluate the expression
            return new net.objecthunter.exp4j.ExpressionBuilder(expression).build().evaluate();
        } catch (Exception e) {
            // Return NaN (Not a Number) if evaluation fails
            return Double.NaN;
        }
    }
}
