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
        vH.cname.setText(cursor.getString(MainActivity.COL_COURSE_NAME));
        vH.cdate.setText(cursor.getString(MainActivity.COL_COURSE_LOCATION));
    }

    public class CourseViewHolder{

        private final TextView cname;
        private final TextView cdate;

        public CourseViewHolder(View view) {
            cname = (TextView) view.findViewById(R.id.tv_course_name);
            cdate = (TextView) view.findViewById(R.id.tv_course_date);
        }
    }

}
