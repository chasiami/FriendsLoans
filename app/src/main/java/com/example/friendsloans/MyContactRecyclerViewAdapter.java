package com.example.friendsloans;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendsloans.contacts.ContactListContent.Contact;

import java.util.List;


public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final ContactFragment.OnContactFragmentInteractionListener CmListener;


    public MyContactRecyclerViewAdapter(List<Contact> items, ContactFragment.OnContactFragmentInteractionListener cmListener) {
        mValues = items;
        CmListener = cmListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Contact con = mValues.get(position);
        holder.mItem = con;
        holder.mContentView.setText(con.name);
        holder.mDescri.setText(con.phone);




        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(null !=CmListener)
                {
                    CmListener.onContactFragmentClickInteraction(holder.mItem,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final TextView mDescri;

        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content_Contact);
            mDescri = (TextView) view.findViewById(R.id.description);

    }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}