package com.example.friendsloans.loans;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.friendsloans.contacts.ContactListContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class LoanListContent {


    public static final List<Loan> ITEMS = new ArrayList<Loan>();
    public static final List<Loan> ITEMS_DEBIT = new ArrayList<Loan>();
    public static final List<Loan> ITEMS_CREDIT = new ArrayList<Loan>();
    public static final Map<String, Loan> ITEM_MAP = new HashMap<String, Loan>();
    private static final int COUNT = 0;
    public static int count_loan = 0;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Loan item) {
        Log.i("add", "add item to list" + item.id + " amount " + item.amount);

        ITEMS.add(item);
        if (item.type == "debit" )
        {
            ITEMS_DEBIT.add(item);
        }
        else if(item.type =="credit")
        {
            ITEMS_CREDIT.add(item);
        }
        ITEM_MAP.put(item.id, item);
        count_loan += 1;
    }

    public static String getItem(int position)
    {
        return ITEMS.get(position).amount;
    }

    public static void clearList()
    {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    private static Loan createDummyItem(int position) {
        return new Loan(String.valueOf(position), new ContactListContent.Contact(), makeDetails(position), "10", "debit");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < 1; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class Loan implements Parcelable {
        public final String id;
        public final ContactListContent.Contact contact;
        public final String details;
        public final String amount;
        public final String type;  // false == Debit | true == Credit
        public String picPath;

        public Loan(String id, ContactListContent.Contact content, String details, String amount, String type) {
            this.id = id;
            this.contact = content;
            this.details = details;
            //this.amount = amount;
            this.type = type;
            this.picPath = "basic";

            char tmp = amount.charAt(0);


            if( type == "credit" && tmp != '-')
            {
                this.amount = "-" + amount;
            }
            else
            {
                this.amount = amount;
            }
        }

        protected Loan(Parcel in) {
            id = in.readString();
            String c1 = in.readString();
            String c2 = in.readString();
            String c3 = in.readString();
            String c4=  in.readString();
            contact = new ContactListContent.Contact(c1,c2,c3,c4);
            details = in.readString();
            amount = in.readString();
            type = in.readString();
            picPath = in.readString();
        }

        public String getId()
        {
            return id;
        }

        public static final Creator<Loan> CREATOR = new Creator<Loan>() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public Loan createFromParcel(Parcel in) {
                return new Loan(in);
            }

            @Override
            public Loan[] newArray(int size) {
                return new Loan[size];
            }
        };




        @Override
        public String toString() {
            return id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(contact.id);
            dest.writeString(contact.name);
            dest.writeString(contact.phone);
            dest.writeString(contact.email);
            dest.writeString(details);
            dest.writeString(amount);
            dest.writeString(type);
            dest.writeString(picPath);

        }

        public void setPicPath(String path)
        {
            this.picPath = path;
        }
    }
}