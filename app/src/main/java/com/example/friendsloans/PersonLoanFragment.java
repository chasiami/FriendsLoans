package com.example.friendsloans;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.friendsloans.PersonLoan.PersonLoanContent;

public class PersonLoanFragment extends Fragment {


    private MyPersonLoanRecyclerViewAdapter myAdapter;
    private OnPersonFragmentInteractionListener mListener;


    public PersonLoanFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPersonFragmentInteractionListener) {
            mListener = (OnPersonFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_person, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
           myAdapter = new MyPersonLoanRecyclerViewAdapter(PersonLoanContent.ITEMS, mListener);
            recyclerView.setAdapter(myAdapter);
        }
        return view;
    }

    public void notifyDataChange() {
        myAdapter.notifyDataSetChanged();
    }


    public interface OnPersonFragmentInteractionListener {
        void onPersonFragmentClickInteraction(PersonLoanContent.PersonLoan personLoan, int position);

        void onPersonFragmentLongClickInteraction(int position);
    }
}