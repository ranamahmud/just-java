package com.ranamahmud.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private boolean hasWhippedTopping;
    private boolean hasChocolate;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
//Called when button is clicked
    public void submitOrder(View view) {
        displayQuantity(quantity);
        TextView priceTextView = findViewById(R.id.order_summary_text_view);
        priceTextView.setText(createOrderSummary(quantity));
      //  priceTextView.setText("Total price: "+NumberFormat.getCurrencyInstance().format(quantity*5)+"\nThank you!");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Coffee");
        intent.putExtra(Intent.EXTRA_STREAM, createOrderSummary(quantity));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
//displays value
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = findViewById(R.id.price_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {

if(quantity==100)
{
    Toast.makeText(this, "You can't have more than 100 cup coffee.", Toast.LENGTH_SHORT).show();
    return;
}
        quantity += 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity));

    }

    public void decrement(View view) {
        if(quantity==1){
            Toast.makeText(this, "You can't have less than 1 cup of coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity -= 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(quantity));
    }
    private int calculatePrice(int quantity){
        int total = quantity * 5;
        CheckBox hasTopping = findViewById(R.id.checkboxToppings);
        hasWhippedTopping = hasTopping.isChecked();
        CheckBox hasChocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        hasChocolate = hasChocolateCheckBox.isChecked();
        if(hasWhippedTopping==true){
            total += 1;
        }
        if(hasChocolate==true){
            total += 2;
        }
        return total;
    }
    private String createOrderSummary(int quantity){
        EditText editTextName = findViewById(R.id.editTextName);
        name = String.valueOf(editTextName.getText());
        String summary = name + "\nQuantity: "+quantity+"\n"
                + "Add chocolate?"+hasChocolate+"\n"
                +"Add whipped cream?"+hasWhippedTopping+"\n"

                +"Total :"+quantity*5+"!";
        return summary;
    }
}
