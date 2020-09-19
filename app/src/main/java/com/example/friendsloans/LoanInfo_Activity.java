package com.example.friendsloans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.friendsloans.loans.LoanListContent;

import java.util.Objects;

public class LoanInfo_Activity extends AppCompatActivity implements PayFragment.OnPayDialogInteractionListener{

    public static String DATA_CHANGE_KEY = "dataSetChange";
    private boolean imgChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_info_);
        imgChanged = false;
    }

    public void setImgChange(boolean val)
    {
        imgChanged = val;
    }
/*
    @Override
    public void onBackPressed()
    {
        setResult(RESULT_OK, new Intent().putExtra(DATA_CHANGE_KEY, imgChanged));
        super.onBackPressed();
    }
*/
    public void Pay(View view) {

        PayFragment.newInstance().show(getSupportFragmentManager(),getString(R.string.pay_dialog_tag));
    }

    @Override
    public void onDialogPositive(PayFragment dialog) {



        int position = ShowLoan.current_position;

        Intent end = new Intent();
        setResult(RESULT_OK, end);
        Log.i("remove", "size before " + LoanListContent.ITEMS.size());
        finish();

    }

    @Override
    public void onDialogNegative(PayFragment dialog) {
        Toast.makeText(this, "Payment canceled", Toast.LENGTH_LONG).show();
    }
}