package com.example.friendsloans;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.friendsloans.contacts.ContactListContent;


public class ContactFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private MyContactRecyclerViewAdapter myContactRecyclerViewAdapter;
    private OnContactFragmentInteractionListener CmListener;


    public ContactFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            myContactRecyclerViewAdapter = new MyContactRecyclerViewAdapter(ContactListContent.ITEMS, CmListener);
            recyclerView.setAdapter(myContactRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContactFragmentInteractionListener) {
            CmListener = (OnContactFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CmListener = null;
    }

    public void notifyDataChange()
    {
        myContactRecyclerViewAdapter.notifyDataSetChanged();
    }

    public interface OnContactFragmentInteractionListener {
        void onContactFragmentClickInteraction(ContactListContent.Contact contact, int position);
        void onContactFragmentLongClickInteraction(int position);
    }

}