package com.danielledanskin.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/*
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean hasWhippedCream;
    boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int price = 5;

        if (hasWhippedCream) {
            price++;
        }
        if (hasChocolate) {
            price++;
        }

        return quantity * price;
    }

    // This method is called when the order button is clicked.
    public void submitOrder(View view) {
        updateToppings();
        int price = calculatePrice();
        String orderSummaryMessage = createOrderSummary(price);
        displayMessage(orderSummaryMessage);
    }

    // This method creates the order summary as a string and returns it
    private String createOrderSummary(int price) {
        EditText userName = (EditText) findViewById(R.id.name_input);
        String orderSummary = "Name: " + userName.getText();
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nAdd Whipped Cream? " + hasWhippedCream;
        orderSummary += "\nAdd Chocolate? " + hasChocolate;
        orderSummary += "\nTotal = $" + price;
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    // This method updates the topping variables based on checkbox states
    private void updateToppings() {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckbox.isChecked();
    }

    // This method displays the given quantity value on the screen.
    private void displayQuantity(int count) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + count);
    }

    // This method increases the quantity varaible by one
    public void increment(View view) {
        if (quantity <= 100) {
            quantity++;
        }
        displayQuantity(quantity);
    }

    // This method decrements the quantity variable by one
    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
        }
        displayQuantity(quantity);
    }

    // This method displays the order summary to the order summary text view
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
