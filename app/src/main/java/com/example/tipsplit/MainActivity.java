package com.example.tipsplit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText billAmount;
    private TextView tipAmount;
    private TextView totalWithTip;
    private EditText noOfPerson;
    private TextView amountPerPerson;
    private TextView overage;
    private RadioGroup radioGroup;

    private double tipPercent;
    private double totalAmountWithTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            Toast.makeText(this, "savedInstanceState is NULL", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "savedInstanceState is NOT NULL", Toast.LENGTH_LONG).show();

        billAmount = findViewById(R.id.billTotalWithTax);
        tipAmount = findViewById(R.id.tipamount);
        totalWithTip = findViewById(R.id.totalwithtip);
        noOfPerson = findViewById(R.id.numberOfPeople);
        amountPerPerson = findViewById(R.id.totalperperson);
        overage = findViewById(R.id.overage);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void setTipPercent12(View v){
    this.tipPercent = 12;
    this.calculateTipAmount(this.tipPercent);
    }

    public void setTipPercent15(View v){
        this.tipPercent = 15;
        this.calculateTipAmount(this.tipPercent);
    }

    public void setTipPercent18(View v){
        this.tipPercent = 18;
        this.calculateTipAmount(this.tipPercent);
    }

    public void setTipPercent20(View v){
        this.tipPercent = 20;
        this.calculateTipAmount(this.tipPercent);
    }

    private void calculateTipAmount(double tipPercentToCal){

        double tipAmount, totalWithTip;

        String ba = this.billAmount.getText().toString();

        if(ba.isEmpty()) {
            this.radioGroup.clearCheck();
            return;
        }
        double ba1 = Double.parseDouble(ba);

        tipAmount = ba1 * (tipPercentToCal / 100);
        totalWithTip = ba1 + tipAmount;
        this.setTotalAmountWithTip(totalWithTip);

        this.tipAmount.setText(String.format("$%.2f", tipAmount));
        this.totalWithTip.setText(String.format("$%.2f", totalWithTip));
    }

    private void setTotalAmountWithTip(double ta){
        this.totalAmountWithTip = ta;
    }

    private double getTotalAmountWithTip(){
        return this.totalAmountWithTip;
    }

    public void calculateTotalPerPerson(View v){

        double amountPerPerson, overage, ta;

        ta = this.getTotalAmountWithTip();

        String np = this.noOfPerson.getText().toString();

        if(np.isEmpty()) return;
        int np1 = Integer.parseInt(np);

        if(np1 <= 0) {
            this.noOfPerson.getText().clear();
            return;
        }

        amountPerPerson = ta / np1;

        Double frmtamount = Math.round(amountPerPerson * 100.0) / 100.0;

        if(frmtamount < amountPerPerson)
        {
            frmtamount = frmtamount + 0.01;
        }

        this.amountPerPerson.setText(String.format("$%.2f", amountPerPerson));

        overage = (frmtamount * np1) - ta;

        this.overage.setText(String.format("$%.2f",overage));

    }

    public void clearField(View v){
            this.billAmount.getText().clear();
            this.tipAmount.setText("");
            this.totalWithTip.setText("");
            this.noOfPerson.getText().clear();
            this.amountPerPerson.setText("");
            this.overage.setText("");
            this.radioGroup.clearCheck();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

    outState.putString("TIPAMOUNT", tipAmount.getText().toString());
    outState.putString("TOTALWITHTIP", totalWithTip.getText().toString());
    outState.putString("AMOUNTPERPERSON", amountPerPerson.getText().toString());
    outState.putString("OVERAGE", overage.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        tipAmount.setText(savedInstanceState.getString("TIPAMOUNT"));
        totalWithTip.setText(savedInstanceState.getString("TOTALWITHTIP"));
        amountPerPerson.setText(savedInstanceState.getString("AMOUNTPERPERSON"));
        overage.setText(savedInstanceState.getString("OVERAGE"));

    }


}