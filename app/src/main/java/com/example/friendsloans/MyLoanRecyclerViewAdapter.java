package com.example.friendsloans;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendsloans.loans.LoanListContent.Loan;

import java.util.List;


public class MyLoanRecyclerViewAdapter extends RecyclerView.Adapter<MyLoanRecyclerViewAdapter.ViewHolder> {

    private final List<Loan> mValues;
    private final LoanFragment.OnListFragmentInteractionListener mListener;

    public MyLoanRecyclerViewAdapter(List<Loan> items, LoanFragment.OnListFragmentInteractionListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Loan loan = mValues.get(position);
        holder.mItem = loan;


        holder.Mid.setText(loan.contact.name);
        holder.mMoney.setText(loan.amount);

        Context context = holder.mView.getContext();

        Drawable photoDrawable;
        if(loan.type.equals("debit"))
        {
            photoDrawable = context.getResources().getDrawable(R.drawable.upper);
        }
        else if(loan.type.equals("credit"))
        {
            photoDrawable = context.getResources().getDrawable(R.drawable.bottom);
        }
        else
            photoDrawable = context.getResources().getDrawable(R.drawable.upper);

        holder.photo.setImageDrawable(photoDrawable);

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(null !=mListener)
                {
                    mListener.onListFragmentClickInteraction(holder.mItem,position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mMoney;
        public final TextView Mid;
        public final ImageView photo;
        public Loan mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMoney = (TextView) view.findViewById(R.id.money);
            Mid = (TextView) view.findViewById(R.id.content);
            photo = (ImageView) view.findViewById(R.id.item_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMoney.getText() + "'";
        }
    }
}