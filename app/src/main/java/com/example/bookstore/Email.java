package com.example.bookstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Email#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Email extends Fragment {
    EditText mSubjectEt, mMessageEt;
    Button mSendEmailBtn;
    TextView mRecipientEt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_email, container, false);
        mRecipientEt = rootView.findViewById(R.id.recipientEt);
        mSubjectEt = rootView.findViewById(R.id.subjectEt);
        mMessageEt = rootView.findViewById(R.id.messageEt);
        mSendEmailBtn = rootView.findViewById(R.id.sendEmailBtn);

        mSendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient = mRecipientEt.getText().toString().trim();
                String subject = mSubjectEt.getText().toString().trim();
                String message = mMessageEt.getText().toString().trim();

                sendEmail(recipient, subject, message);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void sendEmail(String recipient, String subject, String message) {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");

        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an Email App"));

        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
