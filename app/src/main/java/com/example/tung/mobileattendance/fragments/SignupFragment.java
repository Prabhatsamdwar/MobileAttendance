package com.example.tung.mobileattendance.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;

import com.example.tung.mobileattendance.R;


public class SignUpFragment extends Fragment {

    private Button button;

    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_signup, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Sign Up");
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white_18dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        button= (Button)view.findViewById(R.id.sign_button);
        final android.support.design.widget.TextInputEditText usernameEditText= (android.support.design.widget.TextInputEditText) view.findViewById(R.id.username1_layout);
        final android.support.design.widget.TextInputEditText emailEditText= (android.support.design.widget.TextInputEditText) view.findViewById(R.id.email_layout);
        final android.support.design.widget.TextInputEditText passwordEditText= (android.support.design.widget.TextInputEditText) view.findViewById(R.id.password_layout);
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) view.findViewById(R.id.username_layout);
        final TextInputLayout textInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.email_input_layout);
        final TextInputLayout textInputLayoutPassword = (TextInputLayout) view.findViewById(R.id.password_input_layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=usernameEditText.getText().toString();
                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();

                if(TextUtils.isEmpty(username)){
                    textInputLayoutUserName.setError("Username can't be empty !");
                    return;
                }else{
                    textInputLayoutUserName.setErrorEnabled(false);
                }
                if(TextUtils.isEmpty(email)){
                    textInputLayoutEmail.setError("E-mail id can't be empty !");
                    return;
                }else {
                    textInputLayoutEmail.setErrorEnabled(false);
                }
                if(TextUtils.isEmpty(password)){
                    textInputLayoutPassword.setError("Password can't be empty !");
                    return;
                }else {
                    textInputLayoutPassword.setErrorEnabled(false);
                }
                Log.d("button","login added");
                mListener.addNewUserToDataBase(username,email,password);
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
        void addNewUserToDataBase(String userName,String email,String password);
    }
}
