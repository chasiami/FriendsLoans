package com.example.friendsloans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.friendsloans.PersonLoan.PersonLoanContent;
import com.example.friendsloans.contacts.ContactListContent;
import com.example.friendsloans.loans.LoanListContent;

public class AddLoan_value extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch mySwitch = null;
    String type = "debit"; // false == Debit | true == Credit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan_value);



        mySwitch = (Switch) findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            type = "credit";
        }
        else
        {
            type = "debit";
        }
    }


    public void addLoan(View view) {
        EditText amount_e = (EditText) findViewById(R.id.amount_editText);
        EditText description_e = (EditText) findViewById(R.id.description_editText);

        String amount = amount_e.getText().toString();
        String description = description_e.getText().toString();

        ContactListContent.Contact contact = getIntent().getParcelableExtra("Intent_value");

        int tmp = Integer.parseInt(amount);
        if(type.equals("credit"))
        {
            tmp = -tmp;
        }
        PersonLoanContent.PersonLoan person = new PersonLoanContent.PersonLoan("Person" + MainActivity.person_c, contact, tmp);

        Log.i("Person_example", "person created" + MainActivity.person_c + " con = " + contact.phone + " amount " + amount + "  " +tmp) ;


        PersonLoanContent.addItem(person);


        String ID = "Loan"+ LoanListContent.ITEMS.size() +1;
        LoanListContent.Loan loan = new LoanListContent.Loan(ID,contact,description,amount, type);
        LoanListContent.addItem(loan);

        Intent intent = new Intent();
        intent.putExtra("juhuu",loan );
        setResult(RESULT_OK,intent);




        amount_e.setText("");
        description_e.setText("");
        finish();

    }


}