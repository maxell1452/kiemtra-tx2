package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CODE = 1;
    public static final int UPDATE_CODE = 2;
    public static int pos = -1;

    Button btn_add;
    ListView lv_people;
    ArrayAdapter<Person> adapter;
    List<Person> people;
    MyDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDb(this);
        people = db.getPeople();


        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddActivity.class), ADD_CODE);
            }
        });

        lv_people = findViewById(R.id.lv_people);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, people);
        lv_people.setAdapter(adapter);

        lv_people.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person p = people.get(i);
                db.deletePerson(p);
                adapter.notifyDataSetChanged();
                people.remove(i);
                Toast.makeText(MainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        lv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("updated", people.get(i));
                startActivityForResult(intent, UPDATE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_CODE) {
                Person s = (Person) data.getSerializableExtra("new_person");
                people.add(s);
                db.insertPerson(s);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
            if (requestCode == UPDATE_CODE) {
                if (pos >= 0) {
                    Person s = (Person) data.getSerializableExtra("updated");
                    people.set(pos, s);
                    adapter.notifyDataSetChanged();
                    db.updatePerson(s);
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

                }

            }
        }
    }
}