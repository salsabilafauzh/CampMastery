package com.example.CampMastery.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.CampMastery.Activities.LoginActivity;
import com.example.CampMastery.Activities.MainActivity;
import com.example.CampMastery.Model.User;
import com.example.CampMastery.R;
import com.example.CampMastery.db.DbHelper_User;
import com.google.android.material.badge.BadgeUtils;

import java.util.List;
public class ProfileFragment extends Fragment implements View.OnClickListener {

    private String email, username;
    TextView tvUsername, tvEmail;
    Button btnLogout;

    public ProfileFragment(String email, String username) {
        // Required empty public constructor
        this.email = email;
        this.username = username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find TextViews by their IDs
        tvUsername = view.findViewById(R.id.Username);
        tvEmail = view.findViewById(R.id.Email);
        btnLogout = view.findViewById(R.id.btn_logout);

        // Set the text values
        tvEmail.setText(email);
        tvUsername.setText(username);

        // Set the click listener for the logout button
        btnLogout.setOnClickListener(this);

        return view; // Return the modified view
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_logout) {
            // Handle logout button click
            Intent logout = new Intent(getActivity(), LoginActivity.class);
            logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logout);
            getActivity().finish();
        }
    }
}
