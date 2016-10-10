package com.example.tung.mobileattendance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tung on 10/10/16.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ListViewHolder>{


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.header.setText("Operating Syestem");
        holder.note.setText("MCA");



    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView header;
        private TextView note;

        public ListViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.header_text);
            note = (TextView) itemView.findViewById(R.id.content_text);
        }
    }
}
