package com.example.tung.mobileattendance.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.mobileattendance.OnListItemClickListener;
import com.example.tung.mobileattendance.R;
import com.example.tung.mobileattendance.models.Course;

import java.util.List;

/**
 * Created by tung on 10/10/16.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ListViewHolder> {
    private static final String TAG = "HomeListAdapter";

    private List<Course> courseList;
    private OnListItemClickListener onListItemClickListener;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_item, parent, false);
        return new ListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {

        holder.courseName.setText(courseList.get(position).getTitle());
        holder.className.setText(courseList.get(position).getClassName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HomeListAdapter", "onListItemClick HomeList courseId = "+courseList.get(position).getId());
                onListItemClickListener.onListItemClick(view, courseList.get(position).getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;
        private TextView className;

        public ListViewHolder(View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.header_text);
            className = (TextView) itemView.findViewById(R.id.content_text);
        }
    }

}
