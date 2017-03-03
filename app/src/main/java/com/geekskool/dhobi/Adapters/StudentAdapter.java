package com.geekskool.dhobi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.dhobi.Models.Student;
import com.geekskool.dhobi.R;

import java.util.List;

/**
 * Created by manisharana on 19/2/17.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_row, parent, false);

        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.sName.setText(student.getName());
        holder.sRoomNumber.setText(String.valueOf(student.getRoomNumber()));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        private final TextView sName;
        private final TextView sRoomNumber;

        public StudentViewHolder(View view) {
            super(view);
            sName = (TextView) view.findViewById(R.id.tv_student_name);
            sRoomNumber = (TextView) view.findViewById(R.id.tv_student_room_number);
        }
    }


}
