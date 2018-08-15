package com.example.tazo.testforfirebase;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    Uri file;
    FirebaseStorage storage;
    StorageReference storageReference;
    UploadTask uploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();

        int PerCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);


        if(PerCheck == PackageManager.PERMISSION_DENIED){
            perCheck();
        }

    }
    public void init(){
        findViewById(R.id.btn_upload).setOnClickListener(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }
    void perCheck(){
        ActivityCompat.requestPermissions(this,
                new String[]{
                        android.Manifest.permission.READ_CONTACTS,
                        android.Manifest.permission.WRITE_CONTACTS,
                        android.Manifest.permission.INTERNET
                },
                0
        );
    }
    @Override
    public void onClick(View view) {
        file = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Documents/SF_SM/Text/서울창업허브-37.54682249999999-126.9500658-0-(1).txt"));
        StorageReference uploadRef = storageReference.child("SF_SM/Text/서울창업허브-37.54682249999999-126.9500658-0-(1).txt");

        uploadTask = uploadRef.putFile(file);
        uploadTask
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(getApplicationContext(),downloadUri.toString(),Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
