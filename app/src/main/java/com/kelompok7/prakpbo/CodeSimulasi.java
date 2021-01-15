package com.kelompok7.prakpbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CodeSimulasi extends AppCompatActivity {

    LinearLayout btn_back, btn_lanjut;
    EditText input_code_sumlasi;
    ImageView btn_cari;
    TextView read_proc, read_mobo, read_ram, read_vga, read_psu, read_storage;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_simulasi);

        input_code_sumlasi = findViewById(R.id.input_code_simulasi);

        read_proc = findViewById(R.id.read_proc);
        read_mobo = findViewById(R.id.read_mobo);
        read_ram = findViewById(R.id.read_ram);
        read_vga = findViewById(R.id.read_vga);
        read_psu = findViewById(R.id.read_psu);
        read_storage = findViewById(R.id.read_storage);

        btn_cari = findViewById(R.id.btn_cari);
        btn_back = findViewById(R.id.btn_back_code);
        btn_lanjut = findViewById(R.id.btn_lanjut);

        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String proc = dataSnapshot.child("code").child(input_code_sumlasi.getText().toString()).
                                child("processore").getValue().toString();
                        String mobo = dataSnapshot.child("code").child(input_code_sumlasi.getText().toString()).
                                child("motherboard").getValue().toString();
                        String ram = dataSnapshot.child("code").child(input_code_sumlasi.getText().toString()).
                                child("ram").getValue().toString();
                        String vga = dataSnapshot.child("code").child(input_code_sumlasi.getText().toString()).
                                child("vga").getValue().toString();
                        String psu = dataSnapshot.child("code").child(input_code_sumlasi.getText().toString()).
                                child("psu").getValue().toString();
                        String storage = dataSnapshot.child("code").child(input_code_sumlasi.getText().toString()).
                                child("storage").getValue().toString();

                        read_proc.setText(proc);
                        read_mobo.setText(mobo);
                        read_ram.setText(ram);
                        read_vga.setText(vga);
                        read_psu.setText(psu);
                        read_storage.setText(storage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(input_code_sumlasi.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/

                    input_code_sumlasi.setError( "Code Simulasi is required!" );

                }else{
                    Intent gotoload = new Intent(CodeSimulasi.this, UpdateSimulasi.class);
                    gotoload.putExtra("code", "" + input_code_sumlasi.getText());
                    startActivity(gotoload);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(CodeSimulasi.this,Home.class);
                startActivity(gotohome);
            }
        });
    }
}