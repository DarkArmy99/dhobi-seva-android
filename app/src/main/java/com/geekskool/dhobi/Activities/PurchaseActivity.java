package com.geekskool.dhobi.Activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.geekskool.dhobi.Contracts.Contract;
import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 16/2/17.
 */
public class PurchaseActivity extends AppCompatActivity {


    private EditText mName;
    private EditText mQuantity;
    private EditText mRate;
    private EditText mDate;
    private static final int PURCHASE_LOADER_ID = 1;
    public static final int COL_PURCHASE_ID = 0;
    public static final int COL_PURCHASE_NAME = 1;
    public static final int COL_PURCHASE_QUANTITY = 2;
    public static final int COL_PURCHASE_RATE = 3;

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

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.PurchaseEntry.COLUMN_NAME,name);
        contentValues.put(Contract.PurchaseEntry.COLUMN_DATE,date);
        contentValues.put(Contract.PurchaseEntry.COLUMN_QUANTITY,quantity);
        contentValues.put(Contract.PurchaseEntry.COLUMN_RATE,rate);
        getContentResolver().insert(Contract.PurchaseEntry.CONTENT_URI, contentValues);
    }



}
