package com.example.tung.mobileattendance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class AddCourseFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private OnFragmentInteractionListener mListener;

    public AddCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Courses");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_navigate_before_white_18dp);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        final EditText courseEditText = (EditText) view.findViewById(R.id.course_edit_text);
        final EditText classEditText = (EditText) view.findViewById(R.id.class_edit_text);
        final EditText sectionEditText = (EditText) view.findViewById(R.id.section_edit_text);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = courseEditText.getText().toString();
                String className = classEditText.getText().toString();
                String sectionName = sectionEditText.getText().toString();

                if (TextUtils.isEmpty(courseName)) {
                    courseEditText.setError("Course name can't be empty !");
                    return;
                }
                if (TextUtils.isEmpty(className)) {
                    classEditText.setError("Class name can't be empty !");
                    return;
                }
                if (TextUtils.isEmpty(sectionName)) {
                    sectionEditText.setError("Section name can't be empty !");
                    return;
                }
                Log.d("Fab", "course added");
                getFragmentManager().popBackStack();
                mListener.addDataToDataBase(courseName, className, sectionName);
            }
        });

        return view;

    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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
        void addDataToDataBase(String title, String className, String section);
    }
}
