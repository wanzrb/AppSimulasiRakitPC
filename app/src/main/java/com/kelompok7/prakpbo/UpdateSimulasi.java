package com.kelompok7.prakpbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UpdateSimulasi extends AppCompatActivity {

    TextView load_code_input;
    Spinner update_proc, update_mobo, update_ram, update_vga, update_psu, update_storage;
    LinearLayout btn_back, btn_save;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Context context = this;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private ArrayList<String> arrayList2 = new ArrayList<>();
    private ArrayList<String> arrayList3 = new ArrayList<>();
    private ArrayList<String> arrayList4 = new ArrayList<>();
    private ArrayList<String> arrayList5 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_simulasi);

        load_code_input = findViewById(R.id.load_code_input);

        update_proc = findViewById(R.id.load_processore);
        update_mobo = findViewById(R.id.load_motherboard);
        update_ram = findViewById(R.id.load_ram);
        update_vga = findViewById(R.id.load_vga);
        update_psu = findViewById(R.id.load_psu);
        update_storage = findViewById(R.id.load_storage);

        btn_save = findViewById(R.id.btn_simpan_load);
        btn_back = findViewById(R.id.btn_back_load);

        Bundle extras = getIntent().getExtras();
        String code_input = extras.getString("code");
        load_code_input.setText(code_input);

        load_code_input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(load_code_input.getText());
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        showDataProcessore();
        showDataMotherboard();
        showDataRam();
        showDataPsu();
        showDataStorage();
        showDataVga();
        
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("code").child(load_code_input.getText().toString()).
                                child("processore").setValue(update_proc.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(load_code_input.getText().toString()).
                                child("motherboard").setValue(update_mobo.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(load_code_input.getText().toString()).
                                child("ram").setValue(update_ram.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(load_code_input.getText().toString()).
                                child("vga").setValue(update_vga.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(load_code_input.getText().toString()).
                                child("psu").setValue(update_psu.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(load_code_input.getText().toString()).
                                child("storage").setValue(update_storage.getSelectedItem());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent gotocode = new Intent(UpdateSimulasi.this,CodeSimulasi.class);
                startActivity(gotocode);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(UpdateSimulasi.this,CodeSimulasi.class);
                startActivity(gotohome);
            }
        });
    }


    private void showDataVga() {
        databaseReference.child("vgas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList5.add(item.child("vga").getValue(String.class));
                }
            
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateSimulasi.this,R.layout.style_spinner,arrayList5);
                update_vga.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDataStorage() {
        databaseReference.child("storages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList4.add(item.child("storage").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateSimulasi.this,R.layout.style_spinner,arrayList4);
                update_storage.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDataPsu() {
        databaseReference.child("psus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList3.add(item.child("psu").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateSimulasi.this,R.layout.style_spinner,arrayList3);
                update_psu.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDataRam() {
        databaseReference.child("rams").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList2.add(item.child("ram").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateSimulasi.this,R.layout.style_spinner,arrayList2);
                update_ram.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDataMotherboard() {
        databaseReference.child("motherboards").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList1.add(item.child("motherboard").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateSimulasi.this,R.layout.style_spinner,arrayList1);
                update_mobo.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDataProcessore() {
        databaseReference.child("processores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList.add(item.child("processore").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UpdateSimulasi.this,R.layout.style_spinner,arrayList);
                update_proc.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}