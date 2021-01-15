package com.kelompok7.prakpbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    LinearLayout btn_rakit, btn_list, btn_about_us, btn_my_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       btn_rakit = findViewById(R.id.btn_simulasi);
       btn_list = findViewById(R.id.btn_list);
       btn_about_us = findViewById(R.id.btn_about_us);
       btn_my_code = findViewById(R.id.btn_my_code);

       btn_rakit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent gotosimulasi = new Intent(Home.this,Simulasi.class);
               startActivity(gotosimulasi);
           }
       });

       btn_list.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent gotocodesim = new Intent(Home.this,CodeSimulasi.class);
               startActivity(gotocodesim);
           }
       });

       btn_about_us.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent gotoabout = new Intent(Home.this,AboutUs.class);
               startActivity(gotoabout);
           }
       });

       btn_my_code.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent gotomycode = new Intent(Home.this,myCode.class);
               startActivity(gotomycode);
           }
       });
    }
}