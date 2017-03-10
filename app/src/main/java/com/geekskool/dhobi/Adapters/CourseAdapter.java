package com.geekskool.dhobi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.dhobi.Activities.MainActivity;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by manisharana on 19/2/17.
 */
public class CourseAdapter extends RealmRecyclerViewAdapter<Course,CourseAdapter.CourseViewHolder> {


    private final MainActivity activity;

    public CourseAdapter(MainActivity activity, OrderedRealmCollection<Course> courseList) {
        super(courseList,true);
        this.activity = activity;
    }

    private String getDateInFormat(long date) {
        Date curr = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(curr);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_row,parent,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course course = getData().get(position);
        holder.course = course;
        holder.name.setText(course.getDescription());
        holder.sDate.setText(getDateInFormat(course.getStartDate()));
        holder.eDate.setText(getDateInFormat(course.getEndDate()));
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView name;
        private final TextView sDate;
        private final TextView eDate;
        private Course course;

        CourseViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_course_name);
            sDate = (TextView) view.findViewById(R.id.tv_course_start_date);
            eDate = (TextView) view.findViewById(R.id.tv_course_end_date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            activity.goToNext(course);
        }
    }

}
