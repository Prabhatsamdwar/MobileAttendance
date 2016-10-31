package com.example.tung.mobileattendance.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.mobileattendance.R;
import com.example.tung.mobileattendance.models.Student;

import java.util.List;

/**
 * Created by tung on 10/10/16.
 *
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ListViewHolder> {
    private static final String TAG = "StudentListAdapter";

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
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.studentName.setText(studentList.get(position).getStudentName());
        holder.rollNo.setText(studentList.get(position).getRollNo());

    }


    @Override
    public int getItemCount()
    {
        return studentList.size();
    }
    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName;
        private TextView rollNo;

        public ListViewHolder(View itemView) {
            super(itemView);
            studentName = (TextView) itemView.findViewById(R.id.header_text);
            rollNo = (TextView) itemView.findViewById(R.id.content_text);
        }
    }


    public interface OnListItemClickListener {
        public void onClick(View view, int noteId);
    }
}
