package com.example.tung.mobileattendance.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tung.mobileattendance.DataBaseHelper;
import com.example.tung.mobileattendance.R;

public class LoginFragment extends Fragment {
    private Button loginButton;
    private DataBaseHelper dataBaseHelperLogin;
    private OnFragmentInteractionListener mListener;
    private TextView signUpTextView;
    private android.support.design.widget.TextInputEditText userNameEditText;
    private android.support.design.widget.TextInputEditText passwordEditText;
    private TextInputLayout textInputLayoutUserName;
    private TextInputLayout textInputLayoutPassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Sign In");


        loginButton = (Button) view.findViewById(R.id.Login_button);
        userNameEditText = (android.support.design.widget.TextInputEditText) view.findViewById(R.id.login_username);
        passwordEditText = (android.support.design.widget.TextInputEditText) view.findViewById(R.id.login_password);
        signUpTextView = (TextView) view.findViewById(R.id.sign_up_textView);
        textInputLayoutUserName = (TextInputLayout) view.findViewById(R.id.username_layout);
        textInputLayoutPassword = (TextInputLayout) view.findViewById(R.id.password_layout);
        dataBaseHelperLogin = new DataBaseHelper(getActivity());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUserName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (TextUtils.isEmpty(loginUserName)) {
                    textInputLayoutUserName.setError("Username can't be empty !");
                    return;
                }else{
                    textInputLayoutUserName.setError(null);
                    textInputLayoutUserName.setErrorEnabled(false);
                }
                if (TextUtils.isEmpty(password)) {
                    textInputLayoutPassword.setError("Password can't be empty !");
                    return;
                }else{
                    textInputLayoutUserName.setError(null);
                    textInputLayoutUserName.setErrorEnabled(false);
                }

                boolean isValidUser = dataBaseHelperLogin.getLogin(loginUserName, password);
                if (isValidUser == true) {
                    getFragmentManager().popBackStack();
                    mListener.onSuccessOpenHomescreen();
                } else {
                    Toast.makeText(getActivity(), "Either your username or password is wrong", Toast.LENGTH_LONG).show();
                }

            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openSignUpScreen();
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

        void onSuccessOpenHomescreen();

        void openSignUpScreen();
    }
}
