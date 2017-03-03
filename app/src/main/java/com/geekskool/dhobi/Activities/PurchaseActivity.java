package com.geekskool.dhobi.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 16/2/17.
 */
public class PurchaseActivity extends AppCompatActivity {


    private EditText mName;
    private EditText mQuantity;
    private EditText mRate;
    private EditText mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        mName = (EditText) findViewById(R.id.et_purchase_name);
        mQuantity = (EditText) findViewById(R.id.et_purchase_quantity);
        mRate = (EditText) findViewById(R.id.et_et_purchase_rate);
        mDate = (EditText) findViewById(R.id.et_et_purchase_date);
    }

    public void savePurchase(View view) {
        String name = mName.getText().toString();
        String date = mDate.getText().toString();
        int quantity = Integer.valueOf(mQuantity.getText().toString());
        int rate = Integer.valueOf(mRate.getText().toString());

    }



}
