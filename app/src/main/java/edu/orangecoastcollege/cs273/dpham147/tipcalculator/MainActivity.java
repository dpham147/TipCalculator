package edu.orangecoastcollege.cs273.dpham147.tipcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    // Associate the controller with the needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    // Associate the controller with the model
    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the controller with the widgets in our app
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        // Define a listener for the amountEditText
        amountEditText.addTextChangedListener(amountTextChangedListener);

        // Define a listener for percentSeekBar
        percentSeekBar.setOnSeekBarChangeListener(percentChangedListener);
    }

    private TextWatcher amountTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            // Try to get the amount from amountEditText
            try {
                if (charSequence.length() != 0){
                    double amount = Double.parseDouble(charSequence.toString()) / 100.0;
                    currentBill.setmAmount(amount);
                }
                else {
                    currentBill.setmAmount(0.0);
                }

            } catch (NumberFormatException e) {
                amountEditText.setText("");
            }

            // No exception, input is valid
            // 1) Set bill amount (amountTextView)
            amountTextView.setText(currency.format(currentBill.getmAmount()));
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Do nothing
        }
    };

    private SeekBar.OnSeekBarChangeListener percentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            // Update the model with the new tip amount
            currentBill.setmTipPercent(i / 100.0);

            // Update the percentTextView
            percentTextView.setText(percent.format(currentBill.getmTipPercent()));

            // Update the views
            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateViews(){
        // 2) Set tip amount (tipTextView)
        tipTextView.setText(currency.format(currentBill.getmTipAmount()));
        // 3) Set total amount (totalAmountTextView)
        totalTextView.setText(currency.format(currentBill.getmTotalAmount()));
    }
}
