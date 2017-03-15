package com.geekskool.dhobi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.dhobi.Helpers.Util;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.R;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by manisharana on 19/2/17.
 */
public class CourseAdapter extends RealmRecyclerViewAdapter<Course,CourseAdapter.CourseViewHolder> {

    public CourseAdapter(List<Course> courseList) {
        super(courseList, true);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_row,parent,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course course = getData().get(position);
        holder.name.setText(course.getDescription());
        holder.sDate.setText(Util.getDateInFormat(course.getStartDate()));
        holder.eDate.setText(Util.getDateInFormat(course.getEndDate()));
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView sDate;
        private final TextView eDate;

        CourseViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_course_name);
            sDate = (TextView) view.findViewById(R.id.tv_course_start_date);
            eDate = (TextView) view.findViewById(R.id.tv_course_end_date);
        }

    }

}
