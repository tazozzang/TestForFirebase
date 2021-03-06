package com.example.tazo.testforfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        initFirebaseDatabase();
    }
    private void initFirebaseDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference("json");
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(childEventListener);
    }

    @Override
    public void onClick(View v) {
        Qtqt qtqt;
        switch (v.getId()){
            case R.id.btn_send:
                qtqt = new Qtqt("이지연", 24.0 , "서울시 광진구");
                databaseReference.child("dlwldus718").setValue(qtqt);
                qtqt = new Qtqt("이미란", 24.0 , "인천시 계양구");
                databaseReference.child("ranz_").setValue(qtqt);
                qtqt = new Qtqt("전소은", 24.0 , "고양시 일산");
                databaseReference.child("jse525").setValue(qtqt);
                break;
            case R.id.btn_delete:
                databaseReference.child("dlwldus718").removeValue();
                break;
            case R.id.btn_update :
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("dlwldus718/name","공호진");
                databaseReference.updateChildren(map);
                break;

        }

    }
}
