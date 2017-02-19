package com.geekskool.manisha.vipsana.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.manisha.vipsana.Models.Course;
import com.geekskool.manisha.vipsana.R;

import java.util.List;

/**
 * Created by manisharana on 19/2/17.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_row, parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.cname.setText(course.getDesc());
        holder.cdate.setText(String.valueOf(course.getStartDate()));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView cname;
        private final TextView cdate;

        public CourseViewHolder(View view) {
            super(view);
            cname = (TextView) view.findViewById(R.id.tv_course_name);
            cdate = (TextView) view.findViewById(R.id.tv_course_date);
        }
    }

}
