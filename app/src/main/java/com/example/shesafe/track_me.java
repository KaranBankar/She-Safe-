package com.example.shesafe;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class track_me extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView locationtextview;
    EditText et1;

    Button b1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_track_me, container, false);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        editor=sharedPreferences.edit();
        String str=sharedPreferences.getString("add","address");


        locationtextview=view.findViewById(R.id.locationTextView);
        et1=view.findViewById(R.id.et1);
        b1=view.findViewById(R.id.btn_send);
        locationtextview.setText(str);

        String s1=et1.getText().toString();
        String s2=locationtextview.getText().toString();

        String msgstr=s1+s2;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://api.whatsapp.com/send?I8eKYqEa9VS9X3rkXVFt8x="+"&text="+msgstr));
                startActivity(i);
            }
        });


        return view;

    }
}