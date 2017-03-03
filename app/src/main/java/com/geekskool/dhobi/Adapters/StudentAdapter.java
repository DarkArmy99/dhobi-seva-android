package com.geekskool.dhobi.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 19/2/17.
 */
public class StudentAdapter extends CursorAdapter {

    public StudentAdapter(Context context, Cursor cursor, int flags) {
        super(context,cursor,flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_row, parent, false);
        itemView.setTag(new StudentViewHolder(itemView));
        return itemView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        StudentViewHolder vH = (StudentViewHolder) view.getTag();
    }

    public class StudentViewHolder{

        private final TextView sName;
        private final TextView sRoomNumber;
        private final TextView sSeatNumber;

        public StudentViewHolder(View view) {
            sName = (TextView) view.findViewById(R.id.tv_student_name);
            sRoomNumber = (TextView) view.findViewById(R.id.tv_student_room_number);
            sSeatNumber = (TextView)view.findViewById(R.id.tv_student_seat_number);
        }
    }


}
