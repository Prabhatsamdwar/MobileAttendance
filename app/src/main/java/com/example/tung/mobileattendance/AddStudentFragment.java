package com.example.tung.mobileattendance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddStudentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddStudentFragment#} factory method to
 * create an instance of this fragment.
 */
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
        View view=inflater.inflate(R.layout.fragment_add_student, container, false);


        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.student_add_float_button);
        final EditText studentNameEditText = (EditText) view.findViewById(R.id.studentName_edit_text);
        final EditText rollnoEditText = (EditText) view.findViewById(R.id.rollno_edit_text);
        final EditText contactEditText = (EditText) view.findViewById(R.id.contact_edit_text);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = studentNameEditText.getText().toString();
                String rollNo = rollnoEditText.getText().toString();
                String contact = contactEditText.getText().toString();

                if (TextUtils.isEmpty(studentName)) {


                    studentNameEditText.setError("student Name name can't be empty !");
                    return;
                }
                if (TextUtils.isEmpty(rollNo)) {
                    rollnoEditText.setError("Roll No name can't be empty !");
                    return;
                }
                if (TextUtils.isEmpty(contact)) {
                    contactEditText.setError("Contact can't be empty !");
                    return;
                }
                Log.d("Fab_student", "student added");
                getFragmentManager().popBackStack();
                mListener.studentAddDataToDatabase(studentName, rollNo, contact);
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
        void studentAddDataToDatabase(String studentName, String rollNo,String contact);
    }
}
