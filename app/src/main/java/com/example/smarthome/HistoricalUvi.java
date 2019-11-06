package com.example.smarthome;
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
import java.util.ArrayList;
import java.util.List;


public class HistoricalUvi extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference uviDatabaseReference;
    private ArrayAdapter<List<PostUvi>> historicalAdapter;
    private Context context = this;
    private String location;
    final private String TAG = "HistoricalUvi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historical_layout);

        this.chargeDatabase();

        Bundle bundle = this.getIntent().getExtras();
        this.location = bundle.getString(getString(R.string.key));
        if(this.location.isEmpty()){
            this.getData();
        }
        else {
            this.getDataLocation();
        }
    }

    public void add(List<String> data){
        this.chargeDatabase();
        FirebaseUvi uvi = new FirebaseUvi(data.get(0), data.get(1), new Float(data.get(2)));
        String key = data.get(0)+ data.get(1);
        this.uviDatabaseReference.child("uvi").child(key.replace(" ","-")).setValue(uvi);
    }

    public void getDataLocation(){
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
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        this.uviDatabaseReference.addChildEventListener(postListener);
    }

    public void getData(){
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
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        this.uviDatabaseReference.addChildEventListener(postListener);
    }

    private void chargeDatabase(){
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        this.uviDatabaseReference = mFirebaseDatabase.getReference();
    }
}
