package com.kelompok7.prakpbo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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

import java.util.ArrayList;
import java.util.Random;

public class Simulasi extends AppCompatActivity {

    private Spinner processore, motherboard, ram, vga, psu, storage;
    TextView code_simulasi;
    Context context = this;
    SharedPreferences sharedPreferences;

    LinearLayout btn_simpan, btn_back;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private ArrayList<String> arrayList2 = new ArrayList<>();
    private ArrayList<String> arrayList3 = new ArrayList<>();
    private ArrayList<String> arrayList4 = new ArrayList<>();
    private ArrayList<String> arrayList5 = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulasi);

        code_simulasi = findViewById(R.id.code_simulasi);

        processore = findViewById(R.id.processore);
        motherboard = findViewById(R.id.motherboard);
        ram = findViewById(R.id.ram);
        vga = findViewById(R.id.vga);
        psu = findViewById(R.id.psu);
        storage = findViewById(R.id.storage);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_back = findViewById(R.id.btn_back);

        showDataProcessore();
        showDataMotherboard();
        showDataRam();
        showDataPsu();
        showDataStorage();
        showDataVga();

        Toast.makeText(context, "Jangan Lupa Simpan Code Simulasi", Toast.LENGTH_LONG).show();

        code_simulasi.setText(givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect());
        code_simulasi.setTextIsSelectable(true);
        code_simulasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(code_simulasi.getText());
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //local storage
                sharedPreferences = getSharedPreferences("PREF_NAME",MODE_PRIVATE);
                sharedPreferences.edit().putString("my_code",code_simulasi.getText().toString()).commit();

                //menyimpan ke database
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("code").child(code_simulasi.getText().toString()).
                                child("processore").setValue(processore.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(code_simulasi.getText().toString()).
                                child("motherboard").setValue(motherboard.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(code_simulasi.getText().toString()).
                                child("ram").setValue(ram.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(code_simulasi.getText().toString()).
                                child("vga").setValue(vga.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(code_simulasi.getText().toString()).
                                child("psu").setValue(psu.getSelectedItem());
                        dataSnapshot.getRef().child("code").child(code_simulasi.getText().toString()).
                                child("storage").setValue(storage.getSelectedItem());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent gotohome = new Intent(Simulasi.this,Home.class);
                startActivity(gotohome);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome2 = new Intent(Simulasi.this,Home.class);
                startActivity(gotohome2);
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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Simulasi.this,R.layout.style_spinner,arrayList5);
                vga.setAdapter(arrayAdapter);
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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Simulasi.this,R.layout.style_spinner,arrayList4);
                storage.setAdapter(arrayAdapter);
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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Simulasi.this,R.layout.style_spinner,arrayList3);
                psu.setAdapter(arrayAdapter);
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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Simulasi.this,R.layout.style_spinner,arrayList2);
                ram.setAdapter(arrayAdapter);
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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Simulasi.this,R.layout.style_spinner,arrayList1);
                motherboard.setAdapter(arrayAdapter);
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

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Simulasi.this,R.layout.style_spinner,arrayList);
                processore.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}