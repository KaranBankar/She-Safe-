package com.example.shesafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.prefs.PreferenceChangeEvent;

public class Emergency extends Fragment {


    ListView listView,listView2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String s[]={"Bidkin Police Staṭion","Narmada Hospital","Sai Hospital","Santaji Police Station",
                "Shanti Hospital","Daiv Bharti Hospital","CSMSS Dental Hospital","CSMSS Ayurvedic Hospital","Binkin Ghati Hospital","Police","Ambulance","Women Helpline"};
    String s1[]={"9307879687","9764999692","9561433272","9876543267","1122334455","6677889900","6655442331","89898769","5566776644","100","108","181"};

    String s2[]={"Chikalthana Police Station","BhavsinghPura Police Station","Cidco Police Station","Hadco Police Station","MGM Hospital",
                    "Sigma Hospital","Government Hospital","N7 Government Hospital","Social Ambulance","Emergency Ambulance","Police"};
    String s3[]={"9307879687","9764999692","9561433272","9876543267","9988776655","1122334455","6677889900","6655442331","89898769","5566776644","9764999692"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_emergency, container, false);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        editor=sharedPreferences.edit();
        String str=sharedPreferences.getString("add","address");
        listView=view.findViewById(R.id.list);
        listView2=view.findViewById(R.id.list2);


        if(str.contains("Farola")){
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,s);
            listView.setAdapter(arrayAdapter);
            ArrayAdapter<String> array=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,s1);
            listView2.setAdapter(array);

        }
        else if(str.contains("Kanchanwadi")){
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,s2);
            listView.setAdapter(arrayAdapter);
            ArrayAdapter<String> array=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,s3);
            listView2.setAdapter(array);
        }

        else{
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,s2);
            listView.setAdapter(arrayAdapter);
            ArrayAdapter<String> array=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,s3);
            listView2.setAdapter(array);
        }

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=String.valueOf(parent.getItemAtPosition(position));
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+str));
                startActivity(i);
            }
        });
        return view;
    }
}