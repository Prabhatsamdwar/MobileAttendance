package com.example.tung.mobileattendance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tung on 10/10/16.
 *
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ListViewHolder> {
    private static final String TAG = "HomeListAdapter";

    private List<Course> courseList;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.courseName.setText(courseList.get(position).getTitle());
        holder.className.setText(courseList.get(position).getClassName());



    }

    @Override
    public int getItemCount()
    {
        return courseList.size();
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
    public interface OnListItemClickListener {
        public void onClick(View view, int noteId);
    }
}
