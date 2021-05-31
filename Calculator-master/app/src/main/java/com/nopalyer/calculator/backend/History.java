package com.nopalyer.calculator.backend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.calculator.R;
import com.nopalyer.calculator.adapter.historyAdapter;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    historyAdapter history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("History");
        listView=findViewById(R.id.lv);
        history=new historyAdapter();
       listView =findViewById(R.id.lv);
       database=FirebaseDatabase.getInstance();
       arrayList=new ArrayList<>();
       adapter=new ArrayAdapter<>(this,R.layout.list_items,R.id.calinfo,arrayList);
       databaseReference=database.getReference("history");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot ds:snapshot.getChildren())
               {
                    history=ds.getValue(historyAdapter.class);
                    arrayList.add(history.getAnswer().toString());
               }
               listView.setAdapter(adapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

}
