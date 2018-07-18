package com.danielledanskin.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/*
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean hasWhippedCream;

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
        if(hasWhippedCream == true){
            return quantity * 6;
        }
        return quantity * 5;
    }

    // This method is called when the order button is clicked.
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckbox.isChecked();
        int price = calculatePrice();
        String orderSummaryMessage = createOrderSummary(price, hasWhippedCream);
        displayMessage(orderSummaryMessage);
    }

    // This method creates the order summary as a string and returns it
    private String createOrderSummary(int price, boolean hasWhippedCream) {
        String orderSummary = "Name: Bob Belcher";
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nAdd Whipped Cream? " + hasWhippedCream;
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
