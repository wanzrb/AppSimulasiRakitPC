package com.kelompok7.prakpbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AboutUs extends AppCompatActivity {

    LinearLayout btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btn_home = findViewById(R.id.btn_back_home_about_us);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(AboutUs.this,Home.class);
                startActivity(gotohome);
            }
        });
    }
}