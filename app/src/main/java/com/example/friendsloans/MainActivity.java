package com.example.friendsloans;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.friendsloans.PersonLoan.PersonLoanContent;
import com.example.friendsloans.contacts.ContactListContent;
import com.example.friendsloans.loans.LoanListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.Person;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    public static final int BUTTON_REG =1;
    public static final String loanExtra = "loanExtra";
    public final String Loan_num = "NumOfLoans";
    public final String Person_num ="NumOfPerson";
    public static String person_c = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);



        FloatingActionButton fab = findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddLoan.class);
                startActivityForResult(intent,BUTTON_REG);
            }
        });

        restoreLoan();
        restorePerson();

        Update_money();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void Update_money()
    {
        TextView all_cash = findViewById(R.id.money_text);
        int count = LoanListContent.ITEMS.size();
        Log.i("count_loan", "count : " + count);
        int sum =0;
        String tmp = "";
        for(int i=0; i<count; i++)
        {
            tmp= LoanListContent.ITEMS.get(i).amount;
            int tmp_int = Integer.parseInt(tmp);
            sum +=tmp_int;
            Log.i("count_loan","sum = " + sum);
        }

        String summ = String.valueOf(sum);
        Log.i("count_loan","sumstrng = " + summ);
        all_cash.setText(summ);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Update_money();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        saveLoan();
        savePerson();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void ShowLoan(View view) {
        Intent intent = new Intent(getApplicationContext(),ShowLoan.class);
        startActivity(intent);
    }



    public void ShowPerson(View view) {
        Intent intent = new Intent(getApplicationContext(),ShowPerson.class);
        startActivity(intent);
    }

    private void saveLoan()
    {
        SharedPreferences loans = getSharedPreferences("LoanShered", MODE_PRIVATE);
        SharedPreferences.Editor editor = loans.edit();

        String ID = "ID_";
        String DETAILS = "DETAIL_";
        String TYPE = "TYPE_";
        String ID_CON = "ID_CON_";
        String NAME = "NAME_";
        String PHONE = "PHONE_";
        String EMAIL = "EMAIL_";
        String AMOUNT = "AMOUNT_";

        editor.clear();
        editor.putInt(Loan_num, LoanListContent.ITEMS.size());

        for(int i=0; i<LoanListContent.ITEMS.size(); i++)
        {
            LoanListContent.Loan loan = LoanListContent.ITEMS.get(i);
            editor.putString(ID + i, loan.id);
            editor.putString(DETAILS +i, loan.details);
            editor.putString(TYPE+i, loan.type);
            editor.putString(ID_CON +i, loan.contact.id);
            editor.putString(NAME +i, loan.contact.name);
            editor.putString(PHONE +i, loan.contact.phone);
            editor.putString(EMAIL +i, loan.contact.email);
            editor.putString(AMOUNT +i, loan.amount);
        }
        editor.apply();
    }



    private void restoreLoan()
    {
      SharedPreferences loan = getSharedPreferences("LoanShered", MODE_PRIVATE);
      int numOfLoan = loan.getInt(Loan_num, 0);
      if(numOfLoan != 0)
      {
          LoanListContent.clearList();
      }

        String ID = "ID_";
        String DETAILS = "DETAIL_";
        String TYPE = "TYPE_";
        String ID_CON = "ID_CON_";
        String NAME = "NAME_";
        String PHONE = "PHONE_";
        String EMAIL = "EMAIL_";
        String AMOUNT = "AMOUNT_";

      for (int i=0; i<numOfLoan; i++)
      {
          String id = loan.getString(ID +i, "0");
          String det = loan.getString(DETAILS +i, "0");
          String typ = loan.getString(TYPE +i, "0");
          String id_con = loan.getString(ID_CON +i, "0");
          String name = loan.getString(NAME +i, "0");
          String phone = loan.getString(PHONE +i, "0");
          String email = loan.getString(EMAIL +i, "0");
          String amount = loan.getString(AMOUNT +i, "0");
          ContactListContent.Contact con = new ContactListContent.Contact(id_con ,name,phone,email);
          LoanListContent.addItem(new LoanListContent.Loan(id,con,det,amount,typ));

      }
    }

    private void savePerson()
    {
        SharedPreferences person = getSharedPreferences("PersonShered", MODE_PRIVATE);
        SharedPreferences.Editor editor = person.edit();

        String ID = "ID_";
        String ID_CON = "ID_CON_";
        String NAME = "NAME_";
        String PHONE = "PHONE_";
        String EMAIL = "EMAIL_";
        String AMOUNT = "AMOUNT_";

        editor.clear();
        editor.putInt(Person_num, PersonLoanContent.ITEMS.size());

        for(int i=0; i<PersonLoanContent.ITEMS.size(); i++)
        {
            PersonLoanContent.PersonLoan personLoan = PersonLoanContent.ITEMS.get(i);
            editor.putString(ID + i, personLoan.id);
            editor.putString(ID_CON +i, personLoan.person.id);
            editor.putString(NAME +i, personLoan.person.name);
            editor.putString(PHONE +i, personLoan.person.phone);
            editor.putString(EMAIL +i, personLoan.person.email);
            editor.putInt(AMOUNT +i, personLoan.amount);
        }
        editor.apply();
    }

    private void restorePerson()
    {
        SharedPreferences person = getSharedPreferences("PersonShered", MODE_PRIVATE);
        int numOfPerson = person.getInt(Person_num, 0);
        if(numOfPerson != 0)
        {
            PersonLoanContent.clearList();
        }

        String ID = "ID_";
        String ID_CON = "ID_CON_";
        String NAME = "NAME_";
        String PHONE = "PHONE_";
        String EMAIL = "EMAIL_";
        String AMOUNT = "AMOUNT_";

        for (int i=0; i<numOfPerson; i++)
        {
            String id = person.getString(ID +i, "0");
            String id_con = person.getString(ID_CON +i, "0");
            String name = person.getString(NAME +i, "0");
            String phone = person.getString(PHONE +i, "0");
            String email = person.getString(EMAIL +i, "0");
            int amount = person.getInt(AMOUNT +i, 0);
            ContactListContent.Contact con = new ContactListContent.Contact(id_con ,name,phone,email);
            PersonLoanContent.addItem(new PersonLoanContent.PersonLoan(id,con,amount));

        }
    }


}