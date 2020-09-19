package com.example.friendsloans;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.friendsloans.PersonLoan.PersonLoanContent;
import com.example.friendsloans.loans.LoanListContent;

import static com.example.friendsloans.R.id.LoanList;

public class ShowLoan extends AppCompatActivity implements LoanFragment.OnListFragmentInteractionListener {

    public static int current_position = -1;
    private LoanListContent.Loan currentLoan;
    private final String CURRENT_TASK = "CurrentTask";
    public static final String loanExtra = "loanExtra";
    LoanFragment.OnListFragmentInteractionListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_loan);
        Log.i("Loaninfo", "test Loan list loeaded");
        if(savedInstanceState != null)
        {
            currentLoan = savedInstanceState.getParcelable(CURRENT_TASK);
        }
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        if(currentLoan != null)
        {
            outState.putParcelable(CURRENT_TASK, currentLoan);
        }
        super.onSaveInstanceState(outState);
    }




    @Override
    public void onListFragmentClickInteraction(LoanListContent.Loan loan, int position) {
        Log.i("Loaninfo", "test Loan info Click");
        //Toast.makeText(this, "test123", Toast.LENGTH_LONG).show();
       Intent intent = new Intent(getApplicationContext(), LoanInfo_Activity.class);
       intent.putExtra(loanExtra,loan);
       //intent.putExtra("POSITION", position);
       current_position = position;
       startActivityForResult(intent, 1);

    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {

    }

    @SuppressLint("ResourceType")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("remove", "size before " + LoanListContent.ITEMS.size());
        if (resultCode == RESULT_OK) {
            //View w = findViewById(R.id.contactsFragment);

            Log.i("remove", "size before " + LoanListContent.ITEMS.size());
            String id = LoanListContent.ITEMS.get(current_position).id;
            PersonLoanContent.deleteItem(LoanListContent.ITEMS.get(current_position));
            LoanListContent.ITEMS.remove(current_position);
            LoanListContent.ITEMS.remove(id);

            Log.i("remove", "size after " + LoanListContent.ITEMS.size());
            ((LoanFragment) getSupportFragmentManager().findFragmentById(LoanList)).notifyDataChange();

            //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            // imm.hideSoftInputFromWindow( w.getWindowToken(),0);


        }
    }
}