package com.geekskool.dhobi.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.geekskool.dhobi.Activities.MainActivity;
import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 19/2/17.
 */
public class PurchaseAdapter extends CursorAdapter {



    public PurchaseAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.purchase_list_row,parent,false);
        view.setTag(new PurchaseViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PurchaseViewHolder vH = (PurchaseViewHolder) view.getTag();
        vH.pName.setText(cursor.getString(MainActivity.COL_COURSE_NAME));
        vH.pDate.setText(cursor.getString(MainActivity.COL_COURSE_LOCATION));
        vH.pAmount.setText(cursor.getString(MainActivity.COL_COURSE_NAME));         // // TODO: 20/2/17  
    }

    public class PurchaseViewHolder{
        private final TextView pName;
        private final TextView pDate;

        private final TextView pAmount;
        public PurchaseViewHolder(View view) {
            pName = (TextView) view.findViewById(R.id.tv_purchase_name);
            pDate = (TextView) view.findViewById(R.id.tv_purchase_date);
            pAmount = (TextView) view.findViewById(R.id.tv_purchase_amount);

        }
    }
}
