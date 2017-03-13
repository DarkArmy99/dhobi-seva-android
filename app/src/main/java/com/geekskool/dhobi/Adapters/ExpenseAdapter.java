package com.geekskool.dhobi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.dhobi.Helpers.Util;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.R;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by manisharana on 19/2/17.
 */
public class ExpenseAdapter extends RealmRecyclerViewAdapter<Expense,ExpenseAdapter.ExpenseViewHolder> {

    public ExpenseAdapter(OrderedRealmCollection list) {
        super(list,true);
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_list_row,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Expense expense = getData().get(position);
        holder.pName.setText(expense.getName());
        holder.pDate.setText(Util.getDateInFormat(expense.getDate()));
        holder.pAmount.setText(String.valueOf(expense.getAmount()));
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder{
        private final TextView pName;
        private final TextView pDate;
        private final TextView pAmount;

        public ExpenseViewHolder(View view) {
            super(view);
            pName = (TextView) view.findViewById(R.id.tv_purchase_name);
            pDate = (TextView) view.findViewById(R.id.tv_purchase_date);
            pAmount = (TextView) view.findViewById(R.id.tv_purchase_amount);

        }
    }
}
