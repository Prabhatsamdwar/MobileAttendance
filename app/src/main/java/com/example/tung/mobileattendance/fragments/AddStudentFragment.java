package com.example.tung.mobileattendance.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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

import com.example.tung.mobileattendance.R;

import static com.example.tung.mobileattendance.constants.Constant.COURSE_ID;
import static com.example.tung.mobileattendance.constants.Constant.COURSE_NAME;


public class AddStudentFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    FloatingActionButton floatingActionButton;

    public AddStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white_18dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        final int courseId = (int) getArguments().get(COURSE_ID);
        final String courseName = (String) getArguments().get(COURSE_NAME);
        actionBar.setTitle("Add Student in " + courseName);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.student_add_float_button);
        final TextInputEditText studentNameEditText = (TextInputEditText) view.findViewById(R.id.studentName_edit_text);
        final TextInputEditText rollNoEditText = (TextInputEditText) view.findViewById(R.id.rollno_edit_text);
        final TextInputEditText contactEditText = (TextInputEditText) view.findViewById(R.id.contact_edit_text);
        final TextInputLayout textInputLayoutName = (TextInputLayout) view.findViewById(R.id.s_name_layout);
        final TextInputLayout textInputLayoutRoll = (TextInputLayout) view.findViewById(R.id.s_roll_layout);
        final TextInputLayout textInputLayoutContact = (TextInputLayout) view.findViewById(R.id.s_contact_layout);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = studentNameEditText.getText().toString();
                String rollNo = rollNoEditText.getText().toString();
                String contact = contactEditText.getText().toString();

                if (TextUtils.isEmpty(studentName)) {
                    textInputLayoutName.setError("Student name can't be empty !");
                    return;
                } else {
                    textInputLayoutName.setErrorEnabled(false);
                    textInputLayoutName.setError(null);
                }
                if (TextUtils.isEmpty(rollNo)) {
                    textInputLayoutRoll.setError("Roll No can't be empty !");
                    return;
                } else {
                    textInputLayoutRoll.setErrorEnabled(false);
                    textInputLayoutRoll.setError(null);
                }
                if (TextUtils.isEmpty(contact)) {
                    textInputLayoutContact.setError("Contact can't be empty !");
                    return;
                } else {
                    textInputLayoutContact.setError(null);
                    textInputLayoutContact.setErrorEnabled(false);
                }
                Log.d("Fab_student", "student added");
                getFragmentManager().popBackStack();
                mListener.studentAddDataToDatabase(studentName, rollNo, contact, courseId);
            }
        });

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void studentAddDataToDatabase(String studentName, String rollNo, String contact, int courseId);
    }
}
