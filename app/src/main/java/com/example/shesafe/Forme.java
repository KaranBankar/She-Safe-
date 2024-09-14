package com.example.shesafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Forme extends Fragment {

    CardView b1,b2,b3,b4,b5,b6,b7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_forme, container, false);
        b1=v.findViewById(R.id.b1);
        b2=v.findViewById(R.id.b2);
        b3=v.findViewById(R.id.b3);
        b5=v.findViewById(R.id.b5);
        b6=v.findViewById(R.id.b6);
        b7=v.findViewById(R.id.b7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://medlineplus.gov/ency/article/007458.htm");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.careinsurance.com/blog/health-insurance-articles/all-you-need-to-know-about-government-schemes-for-women");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.psychguides.com/female-specific/treatment/");
            }
        });



        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("http://www.ncw.nic.in/sites/default/files/Booklet-%20Laws%20relating%20to%20Women_0.pdf");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://en.m.wikipedia.org/wiki/Hindu_code_bills#:~:text=The%20Hindu%20code%20bills%20were,implemented%20the%20reforms%20in%201950s");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.womensweb.in/");
            }
        });
        return v;

    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}
