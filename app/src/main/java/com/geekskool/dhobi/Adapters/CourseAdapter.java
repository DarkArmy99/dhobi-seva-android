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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by manisharana on 19/2/17.
 */
public class CourseAdapter extends CursorAdapter{

    public CourseAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_list_row,parent,false);
        view.setTag(new CourseViewHolder(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CourseViewHolder vH = (CourseViewHolder) view.getTag();
        vH.name.setText(cursor.getString(MainActivity.COL_COURSE_NAME));
        long sDate = cursor.getLong(MainActivity.COL_COURSE_START_DATE);
        vH.sDate.setText(getDateInFormat(sDate));
        long eDate = cursor.getLong(MainActivity.COL_COURSE_END_DATE);
        vH.eDate.setText(getDateInFormat(eDate));
    }

    private String getDateInFormat(long date) {
        Date curr = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(curr);
    }

    public class CourseViewHolder{

        private final TextView name;
        private final TextView sDate;
        private final TextView eDate;

        public CourseViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.tv_course_name);
            sDate = (TextView) view.findViewById(R.id.tv_course_start_date);
            eDate = (TextView) view.findViewById(R.id.tv_course_end_date);
        }
    }

}
