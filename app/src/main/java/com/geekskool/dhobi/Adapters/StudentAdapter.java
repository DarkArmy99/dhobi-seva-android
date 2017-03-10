package com.geekskool.dhobi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekskool.dhobi.Activities.StudentListActivity;
import com.geekskool.dhobi.Models.Student;
import com.geekskool.dhobi.R;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by manisharana on 19/2/17.
 */
public class StudentAdapter extends RealmRecyclerViewAdapter<Student,StudentAdapter.StudentViewHolder>{


    private final StudentListActivity activity;

    public StudentAdapter(StudentListActivity activity, OrderedRealmCollection<Student> studentList) {
        super(studentList,true);
        this.activity = activity;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_row, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = getData().get(position);
        holder.student = student;
        holder.sName.setText(student.getName());
        holder.sRoomNumber.setText(student.getRoomNumber());
        holder.sSeatNumber.setText(student.getSeatNumber());
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView sName;
        private final TextView sRoomNumber;
        private final TextView sSeatNumber;
        private Student student;

        public StudentViewHolder(View view) {
            super(view);
            sName = (TextView) view.findViewById(R.id.tv_student_name);
            sRoomNumber = (TextView) view.findViewById(R.id.tv_student_room_number);
            sSeatNumber = (TextView)view.findViewById(R.id.tv_student_seat_number);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            activity.goToNextActivity(student);
        }
    }


}
