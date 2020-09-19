package com.example.friendsloans.PersonLoan;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.friendsloans.MainActivity;
import com.example.friendsloans.contacts.ContactListContent;
import com.example.friendsloans.loans.LoanListContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonLoanContent {


    public static final List<PersonLoan> ITEMS = new ArrayList<PersonLoan>();


    public static final Map<String, PersonLoan> ITEM_MAP = new HashMap<String, PersonLoan>();



    static {

    }

    public static void clearList()
    {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    public static void addItem(PersonLoan item) {

        boolean test = true;

        Log.i("Person_example", "iTEMS SIZE " + ITEMS.size());

            if(ITEMS.size() >0) {
                for (int i = 0; i < ITEMS.size(); i++) {
                    if (ITEMS.get(i).person.phone.equals(item.person.phone) && test) {
                        Log.i("Person_example2", "person edited " + ITEMS.size());
                        PersonLoan tmp = ITEMS.get(i);
                        Log.i("Person", ": " + tmp.person.name + " " + tmp.person.phone + " " + tmp.amount);

                        tmp.amount += item.amount;
                        Log.i("Person", ": " + tmp.person.name + " " + tmp.person.phone + " " + tmp.amount + " item " +  item.amount);
                        ITEMS.set(i, tmp);
                        test = false;
                    }
                }
                if (test)
                {
                    ITEMS.add(item);
                    ITEM_MAP.put(item.id, item);
                    MainActivity.person_c +=1;
                }
            }
            else
            {
                Log.i("Person_example3", "person added" + ITEMS.size());
                ITEMS.add(item);
                ITEM_MAP.put(item.id, item);
                MainActivity.person_c +=1;

            }



        Log.i("Person_example4", "iTEMS SIZE " + ITEMS.size());

    }

    public static void deleteItem(LoanListContent.Loan item)
    {
        boolean test= true;

        if(ITEMS.size() >0) {
            for (int i = 0; i < ITEMS.size(); i++) {
                if (ITEMS.get(i).person.phone.equals(item.contact.phone) && test) {
                        PersonLoan tmp =ITEMS.get(i);
                        int value = Integer.parseInt(item.amount);

                    tmp.amount -= value;
                    if(tmp.amount == 0)
                    {
                        String id = PersonLoanContent.ITEMS.get(i).id;
                      PersonLoanContent.ITEMS.remove(i);
                      PersonLoanContent.ITEM_MAP.remove(id);
                    }
                    else
                    {
                        ITEMS.set(i, tmp);
                    }

                    test = false;
                }

                }
            }
    }




    public static class PersonLoan implements Parcelable {
        public String id ;
        public final ContactListContent.Contact person;
        public final String picPath;
        public int amount;


        public PersonLoan(String id, ContactListContent.Contact con, int a) {
            this.id = id;
            this.person = con;
            this.picPath = "basic";
            this.amount = a;

        }
        protected PersonLoan(Parcel in)
        {
            id =in.readString();
            String c1 = in.readString();
            String c2 = in.readString();
            String c3 = in.readString();
            String c4=  in.readString();
            person = new ContactListContent.Contact(c1,c2,c3,c4);
            picPath = in.readString();
            amount = in.readInt();
        }

        public static final Creator<PersonLoan> CREATOR = new Creator<PersonLoan>() {
            @Override
            public PersonLoan createFromParcel(Parcel in) {
                return new PersonLoan(in);
            }

            @Override
            public PersonLoan[] newArray(int size) {
                return new PersonLoan[size];
            }
        };

        @Override
        public String toString() {
            return person.name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(person.id);
            dest.writeString(person.name);
            dest.writeString(person.phone);
            dest.writeString(person.email);
            dest.writeString(picPath);
            dest.writeInt(amount);
        }
    }
}