package com.example.friendsloans;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendsloans.PersonLoan.PersonLoanContent.PersonLoan;

import java.util.List;


public class MyPersonLoanRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonLoanRecyclerViewAdapter.ViewHolder> {

    private final List<PersonLoan> mValues;
    private final PersonLoanFragment.OnPersonFragmentInteractionListener mListener;

    public MyPersonLoanRecyclerViewAdapter(List<PersonLoan> items, PersonLoanFragment.OnPersonFragmentInteractionListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       PersonLoan Personloan = mValues.get(position);
        holder.mItem = Personloan;

        Log.i("PLVIEW", "Person name " + Personloan.person.name + " " + Personloan.person.phone + " money: "+ Personloan.amount);
        holder.Mid.setText(Personloan.person.name);
        holder.mMoney.setText(String.valueOf(Personloan.amount));

        Context context = holder.mView.getContext();

        Drawable photoDrawable;
        if(Personloan.amount >0)
        {
            photoDrawable = context.getResources().getDrawable(R.drawable.upper);
        }
        else if(Personloan.amount < 0 )
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
                    mListener.onPersonFragmentClickInteraction(holder.mItem,position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                mListener.onPersonFragmentLongClickInteraction(position);
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
        public PersonLoan mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMoney = (TextView) view.findViewById(R.id.moneyALL);
            Mid = (TextView) view.findViewById(R.id.name);
            photo = (ImageView) view.findViewById(R.id.person_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" ;
        }
    }
}