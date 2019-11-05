package com.example.smarthome;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarthome.models.FirebaseUvi;
import com.example.smarthome.models.PostUvi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class HistoricalUvi extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference uviDatabaseReference;
    private ArrayAdapter<List<PostUvi>> historicalAdapter;
    private Context context = this;
    private String location;
    static List<PostUvi> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historical_layout);
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        this.uviDatabaseReference = mFirebaseDatabase.getReference().child("uvi");
        Bundle bundle = this.getIntent().getExtras();
        this.location = bundle.getString(getString(R.string.key));
        Log.i("cargando datos","nueva vista");
        if(this.location.isEmpty()){
            this.getData();
        }
        else {
            this.getDataLocation();
        }

    }

    public void add(List<String> data){
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        this.uviDatabaseReference = mFirebaseDatabase.getReference().child("uvi");
        FirebaseUvi uvi = new FirebaseUvi(data.get(0), data.get(1), new Float(data.get(2)));
        String key = data.get(0)+ data.get(1);
        this.uviDatabaseReference.child(key.replace(" ","-")).setValue(uvi);
    }

    private List<String> formatInfo(FirebaseUvi uvi){
        List<String> data = new ArrayList<>();
        data.add(uvi.getLocation());
        data.add(uvi.getDate_iso());
        data.add(String.valueOf(uvi.getValue()));

        return data;
    }


    public void getDataLocation(){
        listData = new ArrayList<>();
        ChildEventListener postListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                List<PostUvi> tempListData = new ArrayList<>();
                for(DataSnapshot exerciseSnapshot: dataSnapshot.getChildren())
                {
                    PostUvi post = exerciseSnapshot.getValue(PostUvi.class);
                    post.setLocation(post.getLocation().replace("-"," "));
                    if(post.getLocation().equals(location)){
                        tempListData.add(post);
                    }
                }
                historicalAdapter = new HistoricalAdapter(context, R.layout.historical_adapter, tempListData);
                ListView lvRespuesta =(ListView) findViewById(R.id.historical);
                lvRespuesta.setAdapter(historicalAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("cargando datos", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFirebaseDatabase.getReference().addChildEventListener(postListener);
    }

    public void getData(){
        listData = new ArrayList<>();
        ChildEventListener postListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                List<PostUvi> tempListData = new ArrayList<>();
                for(DataSnapshot exerciseSnapshot: dataSnapshot.getChildren())
                {
                    PostUvi post = exerciseSnapshot.getValue(PostUvi.class);
                    post.setLocation(post.getLocation().replace("-"," "));
                    tempListData.add(post);
                }
                historicalAdapter = new HistoricalAdapter(context, R.layout.historical_adapter, tempListData);
                ListView lvRespuesta =(ListView) findViewById(R.id.historical);
                lvRespuesta.setAdapter(historicalAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("cargando datos", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mFirebaseDatabase.getReference().addChildEventListener(postListener);
    }
}
