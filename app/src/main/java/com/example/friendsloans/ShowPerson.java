package com.example.friendsloans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.friendsloans.PersonLoan.PersonLoanContent;

public class ShowPerson extends AppCompatActivity implements PersonLoanFragment.OnPersonFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);
    }

    @Override
    public void onPersonFragmentClickInteraction(PersonLoanContent.PersonLoan item, int position) {

    }

    @Override
    public void onPersonFragmentLongClickInteraction(int position) {

    }
}