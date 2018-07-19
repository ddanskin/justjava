package com.danielledanskin.justjava;

import android.content.Intent;
import android.net.Uri;
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
    String username;
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

    public void composeOrderEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    // This method is called when the order button is clicked.
    public void submitOrder(View view) {
        updateToppings();
        int price = calculatePrice();
        String orderSummaryMessage = createOrderSummary(price);
        String emailSubject = getString(R.string.email_subject, username);
        composeOrderEmail(emailSubject, orderSummaryMessage );
    }

    // This method creates the order summary as a string and returns it
    private String createOrderSummary(int price) {
        EditText usernameInput = (EditText) findViewById(R.id.name_input);
        username = usernameInput.getText().toString();
        String orderSummary = getString(R.string.order_summary_name, username);
        orderSummary += "\n" + getString(R.string.quantity) + ": " + quantity;
        orderSummary += "\n" + getString(R.string.add_whipped_cream) + hasWhippedCream;
        orderSummary += "\n" + getString(R.string.add_chocolate) + hasChocolate;
        orderSummary += "\n" + getString(R.string.total) + price;
        orderSummary += "\n" + getString(R.string.thank_you);
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
        String totalCount = "" + count;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(totalCount);
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
}
