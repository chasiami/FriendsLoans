package com.example.friendsloans;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PayFragment extends DialogFragment {

    private OnPayDialogInteractionListener mListener;


    public PayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPayDialogInteractionListener) {
            mListener = (OnPayDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeleteDialogInteractionListener");
        }
    }



    static PayFragment newInstance()
    {
        return  new PayFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage(getString(R.string.call_question));

        builder.setPositiveButton(getString(R.string.call_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mListener.onDialogPositive(PayFragment.this);
            }
        });

        builder.setNegativeButton(getString(R.string.call_declain), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mListener.onDialogNegative(PayFragment.this);
            }
        });
        return builder.create();
    }




    public interface OnPayDialogInteractionListener {
        void onDialogPositive(PayFragment dialog);
        void onDialogNegative(PayFragment dialog);
    }



}