package com.geekskool.manisha.vipsana.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.manisha.vipsana.Models.Purchase;
import com.geekskool.manisha.vipsana.R;

import java.util.List;

/**
 * Created by manisharana on 19/2/17.
 */
public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private List<Purchase> purchaseList;

    public PurchaseAdapter(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @Override
    public PurchaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_list_row, parent, false);

        return new PurchaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PurchaseViewHolder holder, int position) {
        Purchase purchase = purchaseList.get(position);
        holder.pName.setText(purchase.getName());
        holder.pDate.setText(String.valueOf(purchase.getDate()));
        holder.pAmount.setText(String.valueOf(purchase.getRate()));         // need to chane later

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }


    public class PurchaseViewHolder extends RecyclerView.ViewHolder{

        private final TextView pName;
        private final TextView pDate;
        private final TextView pAmount;

        public PurchaseViewHolder(View view) {
            super(view);
            pName = (TextView) view.findViewById(R.id.tv_purchase_name);
            pDate = (TextView) view.findViewById(R.id.tv_purchase_date);
            pAmount = (TextView) view.findViewById(R.id.tv_purchase_amount);

        }
    }
}
