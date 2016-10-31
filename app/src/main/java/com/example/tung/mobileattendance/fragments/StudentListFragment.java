package com.example.tung.mobileattendance.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.tung.mobileattendance.R;
import com.example.tung.mobileattendance.adapters.StudentListAdapter;
import com.example.tung.mobileattendance.models.Student;
import com.example.tung.mobileattendance.models.StudentList;

import java.util.List;

import static com.example.tung.mobileattendance.constants.Constant.COURSE_ID;
import static com.example.tung.mobileattendance.constants.Constant.COURSE_NAME;
import static com.example.tung.mobileattendance.constants.Constant.LIST_OF_STUDENTS;


public class StudentListFragment extends Fragment {


    FloatingActionButton floatingActionButton;
    private static StudentList studentList;

    private RecyclerView recyclerView;
    private static StudentListAdapter studentListAdapter;


    private OnFragmentInteractionListener mListener;

    public StudentListFragment() {
        // Required empty public constructor
    }

    public static StudentListFragment newInstance(String param1, String param2) {
        StudentListFragment fragment = new StudentListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        Log.d("StudentListFragment", "openAddStudentScreen()");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final String title = (String) getArguments().get(COURSE_NAME);
        final int courseId = (int) getArguments().get(COURSE_ID);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Students in " + title);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white_18dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setHasOptionsMenu(true);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_student);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
                mListener.openAddStudentScreen(courseId, title);
            }
        });

        studentList = (StudentList) getArguments().getSerializable(LIST_OF_STUDENTS);
        final View viewNoStudent = view.findViewById(R.id.no_students_view);
        if (studentList != null && studentList.getStudentList().size() < 1) {
            /*Display Custom view for add new Student*/
            viewNoStudent.setVisibility(View.VISIBLE);
        }
 /*Init recycler view*/
        recyclerView = (RecyclerView) view.findViewById(R.id.all_student_list);
        studentListAdapter = new StudentListAdapter();
        studentListAdapter.setStudentList(studentList.getStudentList());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(studentListAdapter);
        return view;

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

    public static void refresh(List<Student> studentList) {
        Log.d("StudentListFragment", "refreshing StudentListFragment");
        StudentListFragment.studentList.setStudentList(studentList);
        studentListAdapter.notifyDataSetChanged();


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void openAddStudentScreen(int courseId, String title);
        void getAllStudentByDate(int day, int month, int year);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);  // Use filter.xml from step 1
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.done_action) {
//            showPopUpForDelete(notes);
            Log.d("Menu", "On Click Done Icon");
            return true;
        }
        if (id == R.id.event_action) {
            showPopUpForDatePicker();
            Log.d("Menu", "On Click Calender Icon");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPopUpForDatePicker() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.date_picker_dialog);
        final Button okButton = (Button) dialog.findViewById(R.id.ok_button);
        final Button cancelButton = (Button) dialog.findViewById(R.id.cancel_button);
        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                mListener.getAllStudentByDate(day,month,year);
            }
        });
        dialog.show();
    }
}
