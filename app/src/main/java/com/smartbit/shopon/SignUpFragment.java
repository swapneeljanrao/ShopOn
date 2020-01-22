package com.smartbit.shopon;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }


    private TextView alreadyHaveAccount;
    private FrameLayout parentFrameLayout;

    private EditText mSignUpEmail, mSignUpName, mSignUpPassword, mSignUpConfirmPassword;
    private ImageButton mSignUpCloseButton;
    private Button mSignUp_SignUpButton;
    private ProgressBar mSingnUpProgressbar;
    private FirebaseAuth firebaseAuth;

    private Drawable customDrawableIcon = getActivity().getDrawable(R.mipmap.baseline_error_outline_white_48);

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.register_activity_framelayout);

        alreadyHaveAccount = view.findViewById(R.id.sign_up_tv_already_registered);
        mSignUpEmail = view.findViewById(R.id.sign_up_etEmail);
        mSignUpName = view.findViewById(R.id.sign_up_etName);
        mSignUpPassword = view.findViewById(R.id.sign_up_etPassword);
        mSignUpConfirmPassword = view.findViewById(R.id.sign_up_et_confirmPassword);
        mSignUpCloseButton = view.findViewById(R.id.sign_up_img_btn_close);
        mSignUp_SignUpButton = view.findViewById(R.id.sign_up_btn_sign_up);
        mSingnUpProgressbar = view.findViewById(R.id.sign_up_progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        mSignUpName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSignUpEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSignUpPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSignUpConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mSignUp_SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSignUp_SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailandPassword();
            }
        });

        mSignUpCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkinputs() {
        if (!TextUtils.isEmpty(mSignUpEmail.getText())) {
            if (!TextUtils.isEmpty(mSignUpName.getText())) {
                if (!TextUtils.isEmpty(mSignUpPassword.getText()) && mSignUpPassword.length() >= 8) {
                    if (!TextUtils.isEmpty(mSignUpConfirmPassword.getText())) {
                        mSignUp_SignUpButton.setEnabled(true);
                        mSignUp_SignUpButton.setTextColor(Color.rgb(255, 255, 255));
                    } else {
                        mSignUp_SignUpButton.setEnabled(false);
                        mSignUp_SignUpButton.setTextColor(Color.argb(50, 255, 255, 255));
                    }
                } else {
                    mSignUp_SignUpButton.setEnabled(false);
                    mSignUp_SignUpButton.setTextColor(Color.argb(50, 255, 255, 255));
                }
            } else {
                mSignUp_SignUpButton.setEnabled(false);
                mSignUp_SignUpButton.setTextColor(Color.argb(50, 255, 255, 255));
            }
        } else {
            mSignUp_SignUpButton.setEnabled(false);
            mSignUp_SignUpButton.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

    private void checkEmailandPassword() {

        customDrawableIcon.setBounds(0, 0, customDrawableIcon.getIntrinsicWidth(), customDrawableIcon.getIntrinsicHeight());
        if (mSignUpEmail.getText().toString().matches(emailPattern)) {
            if (mSignUpPassword.getText().toString().equals(mSignUpConfirmPassword.getText().toString())) {

                mSingnUpProgressbar.setVisibility(View.VISIBLE);

                mSignUp_SignUpButton.setEnabled(false);
                mSignUp_SignUpButton.setTextColor(Color.argb(50, 255, 255, 255));

                firebaseAuth.createUserWithEmailAndPassword(mSignUpEmail.getText().toString(), mSignUpPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent mainActivityIntent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(mainActivityIntent);
                                    getActivity().finish();
                                } else {
                                    mSingnUpProgressbar.setVisibility(View.INVISIBLE);
                                    mSignUp_SignUpButton.setEnabled(true);
                                    mSignUp_SignUpButton.setTextColor(Color.rgb(255, 255, 255));

                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), "Error" + error, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            } else {
                mSignUpConfirmPassword.setError("Password and confirm password should be same!!", customDrawableIcon);
            }
        } else {
            mSignUpEmail.setError("Invalid Email format!!", customDrawableIcon);
        }
    }
}
