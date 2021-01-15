package com.kelompok7.prakpbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class myCode extends AppCompatActivity {

    TextView my_code_show;
    LinearLayout back_home;
    SharedPreferences sharedPreferences;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_code);

        my_code_show = findViewById(R.id.my_code);
        back_home = findViewById(R.id.btn_back_my_code);

        SharedPreferences shared = getSharedPreferences("PREF_NAME", MODE_PRIVATE);
        String channel = (shared.getString("my_code", ""));

        my_code_show.setText(channel);
        my_code_show.setTextIsSelectable(true);
        my_code_show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(my_code_show.getText());
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });


        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback = new Intent(myCode.this,Home.class);
                startActivity(goback);
            }
        });
    }
}