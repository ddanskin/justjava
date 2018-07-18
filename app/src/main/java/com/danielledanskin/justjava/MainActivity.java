package com.danielledanskin.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/*
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

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
        return quantity * 5;
    }

    // This method is called when the order button is clicked.
    public void submitOrder(View view) {
        int price = calculatePrice();
        String orderSummaryMessage = createOrderSummary(price);
        displayMessage(orderSummaryMessage);
    }

    // This method creates the order summary as a string and returns it
    private String createOrderSummary(int price) {
        String orderSummary = "Name: Bob Belcher";
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal = $" + price;
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    // This method displays the given quantity value on the screen.
    private void displayQuantity(int count) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + count);
    }

    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
        }
        displayQuantity(quantity);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
