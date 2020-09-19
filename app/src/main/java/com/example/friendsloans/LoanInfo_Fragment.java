package com.example.friendsloans;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendsloans.loans.LoanListContent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class LoanInfo_Fragment extends Fragment implements View.OnClickListener {

   private MyLoanRecyclerViewAdapter myLoanRecyclerViewAdapter;
    public static final int REQUEST = 1;
    private String mPhotoPath;
    private LoanListContent.Loan  displayedLoan;



    public LoanInfo_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_info_, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.displayLoanInfo).setVisibility(View.INVISIBLE);
        getActivity().findViewById(R.id.addPicture).setOnClickListener(this);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            LoanListContent.Loan receivedLoan = intent.getParcelableExtra(MainActivity.loanExtra);
            if (receivedLoan != null) {
                displayLoan(receivedLoan);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST && resultCode == Activity.RESULT_OK)
        {
            FragmentActivity holdingActivity = getActivity();
            if(holdingActivity != null)
            {
                ImageView taskImage = holdingActivity.findViewById(R.id.addPicture);
                Bitmap cameraImage = PicUtils.decodePic(mPhotoPath, taskImage.getWidth(), taskImage.getHeight());
                taskImage.setImageBitmap(cameraImage);
                displayedLoan.setPicPath(mPhotoPath);
                LoanListContent.Loan loan = LoanListContent.ITEM_MAP.get(displayedLoan.id);
                if(loan != null){
                    loan.setPicPath(mPhotoPath);
                }

                if(holdingActivity instanceof MainActivity)
                {
                    ((LoanFragment) holdingActivity.getSupportFragmentManager().findFragmentById(R.id.displayLoanInfo)).notifyDataChange();
                } else if( holdingActivity instanceof LoanInfo_Activity)
                {
                    ((LoanInfo_Activity) holdingActivity).setImgChange(true);
                }

            }
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void displayLoan(final LoanListContent.Loan loan)
    {
        FragmentActivity activity = getActivity();
        (activity.findViewById(R.id.displayLoanInfo)).setVisibility(View.VISIBLE);
        TextView nameTextV = activity.findViewById(R.id.name);
        TextView descTextV = activity.findViewById(R.id.desc);
        TextView cashTextV = activity.findViewById(R.id.cash);
        final ImageView addPhoto = activity.findViewById(R.id.addPicture);

        nameTextV.setText(loan.contact.name);
        descTextV.setText(loan.details);
        cashTextV.setText(loan.amount);
        Drawable drawable = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if(loan.picPath.equals("basic")){
            drawable = requireContext().getDrawable(R.drawable.basic);
            }
            else
            {
                Handler handler = new Handler();
                addPhoto.setVisibility(View.INVISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addPhoto.setVisibility(View.VISIBLE);
                        Bitmap camerImage = PicUtils.decodePic(displayedLoan.picPath, addPhoto.getWidth(), addPhoto.getHeight());
                        addPhoto.setImageBitmap(camerImage);
                    }
                }, 200);

            }
        }
        addPhoto.setImageDrawable(drawable);

        displayedLoan = loan;

    }

    private File createImageFile() throws IOException {
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = displayedLoan.id + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onClick(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile=createImageFile();
            }catch(IOException ex) {

            }
            if(photoFile !=null)
            {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),getString(R.string.myFileprovider), photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST);
            }

        }
    }
}