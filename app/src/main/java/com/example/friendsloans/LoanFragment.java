package com.example.friendsloans;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.friendsloans.loans.LoanListContent;


public class LoanFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";


    private MyLoanRecyclerViewAdapter mRecyclerAdapter;
    private OnListFragmentInteractionListener mListener;

    public LoanFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerAdapter = new MyLoanRecyclerViewAdapter(LoanListContent.ITEMS, mListener);
            recyclerView.setAdapter(mRecyclerAdapter);
        }
        return view;
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
        {
            if(data != null){
                boolean changeDataSet = data.getBooleanExtra(LoanInfo_Activity.DATA_CHANGE_KEY, false);
                if(changeDataSet) notifyDataChange();
            }
        }
    }
*/
    public void notifyDataChange()
    {
        mRecyclerAdapter.notifyDataSetChanged();
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentClickInteraction(LoanListContent.Loan loan, int position);
        void onListFragmentLongClickInteraction(int position);

    }
}