package com.example.tung.mobileattendance.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tung.mobileattendance.R;
import com.example.tung.mobileattendance.models.Student;

import java.util.List;

/**
 * Created by tung on 10/10/16.
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ListViewHolder> {
    private static final String TAG = "StudentListAdapter";

    public List<Student> getStudentList() {
        return studentList;
    }

    private List<Student> studentList;

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new ListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        holder.studentName.setText(studentList.get(position).getStudentName());
        holder.rollNo.setText(studentList.get(position).getRollNo());
        if (studentList.get(position).isAbsent()) {
            holder.absentRadioButton.setChecked(true);
        } else if (studentList.get(position).isOnLeave()) {
            holder.onLeaveRadioButton.setChecked(true);
        } else if (studentList.get(position).isPresent()) {
            holder.presentRadioButton.setChecked(true);
        }
        holder.presenceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) holder.view.findViewById(checkedId);
                Log.d("RadioButton", rb.getText() + "");
                String attendance = rb.getText().toString();
                switch (attendance) {
                    case "A":
                        studentList.get(position).setAbsent(true);
                        studentList.get(position).setPresent(false);
                        studentList.get(position).setOnLeave(false);
                        break;
                    case "P":
                        studentList.get(position).setAbsent(false);
                        studentList.get(position).setPresent(true);
                        studentList.get(position).setOnLeave(false);
                        break;
                    case "L":
                        studentList.get(position).setAbsent(false);
                        studentList.get(position).setPresent(false);
                        studentList.get(position).setOnLeave(true);
                        break;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName;
        private TextView rollNo;
        private RadioButton presentRadioButton;
        private RadioButton absentRadioButton;
        private RadioButton onLeaveRadioButton;
        private RadioGroup presenceRadioGroup;
        private View view;

        public ListViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            studentName = (TextView) itemView.findViewById(R.id.header_text);
            rollNo = (TextView) itemView.findViewById(R.id.content_text);
            presentRadioButton = (RadioButton) itemView.findViewById(R.id.present_radio_button);
            absentRadioButton = (RadioButton) itemView.findViewById(R.id.absent_radio_button);
            onLeaveRadioButton = (RadioButton) itemView.findViewById(R.id.leave_radio_button);
            presenceRadioGroup = (RadioGroup) itemView.findViewById(R.id.presence_radio_group);
        }
    }
}
