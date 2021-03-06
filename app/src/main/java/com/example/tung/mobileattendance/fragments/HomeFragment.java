package com.example.tung.mobileattendance.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.mobileattendance.adapters.HomeListAdapter;
import com.example.tung.mobileattendance.OnListItemClickListener;
import com.example.tung.mobileattendance.R;
import com.example.tung.mobileattendance.models.Course;
import com.example.tung.mobileattendance.models.CourseList;

import java.util.List;


public class HomeFragment extends Fragment implements OnListItemClickListener {
    private CourseList courses;
    private FloatingActionButton floatingActionButton;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private HomeListAdapter homeListAdapter;

    private RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    private CourseList courseList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Mobile Attendance");

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openAddCourseScreen();
            }
        });

        final View noCourseView = view.findViewById(R.id.no_course_view);
        courses = (CourseList) getArguments().getSerializable("List_of_Course");
        final TextView noCourseText = (TextView) view.findViewById(R.id.no_course);
        if (courses != null && courses.getCourseList().size() < 1) {
            noCourseView.setVisibility(View.VISIBLE);
        }else{
            noCourseText.setText("");
        }
 /*Init recycler view*/
        recyclerView = (RecyclerView) view.findViewById(R.id.all_notes_list);
        homeListAdapter = new HomeListAdapter();
        homeListAdapter.setOnListItemClickListener(this);
        homeListAdapter.setCourseList(courses.getCourseList());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeListAdapter);
        return view;

    }

    public void refresh(List<Course> courses) {
        this.courses.setCourseList(courses);
        homeListAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(View view, int courseId) {
        Log.d("HomeFragment", "CourseId = " + courseId);
        mListener.onCourseItemClick(courseId);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void openAddCourseScreen();

        void onCourseItemClick(int courseId);

    }
}
