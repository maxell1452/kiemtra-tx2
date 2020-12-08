package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText edt_name, edt_cmt, edt_note;
    CheckBox cb_readNews, cb_readBook, cb_code;

    RadioGroup radio_degree;
    RadioButton radio_highSchool, radio_college, radio_university;
    Button btn_submit;

    MyDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = new MyDb(this);


        edt_name = findViewById(R.id.edt_name);
        edt_cmt = findViewById(R.id.edit_cmt);
        edt_note = findViewById(R.id.edt_note);

        radio_degree = findViewById(R.id.radio_degree);
        radio_highSchool = findViewById(R.id.radio_highSchool);
        radio_college = findViewById(R.id.radio_college);
        radio_university = findViewById(R.id.radio_university);

        cb_readNews = findViewById(R.id.cb_readNews);
        cb_readBook = findViewById(R.id.cb_readBook);
        cb_code = findViewById(R.id.cb_code);

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = new Person();

                p.setName(edt_name.getText().toString());
                p.setCmt(edt_cmt.getText().toString());

                switch (radio_degree.getCheckedRadioButtonId()) {
                    case R.id.radio_highSchool:
                        p.setDegree(Person.HIGH_SCHOOL_DEGREE);
                        break;
                    case R.id.radio_college:
                        p.setDegree(Person.COLLEGE_DEGREE);
                        break;
                    case R.id.radio_university:
                        p.setDegree(Person.UNIVERSITY_DEGREE);
                        break;
                }
                String[] fav = new String[]{
                        "", "", ""
                };

                if (cb_readNews.isChecked()) {
                    fav[0] = Person.READ_NEWS;
                }
                if (cb_readBook.isChecked()) {
                    fav[1] = Person.READ_BOOK;
                }
                if (cb_code.isChecked()) {
                    fav[2] = Person.CODE;
                }

                p.setFav(fav);

                p.setNote(edt_note.getText().toString());


                Intent data = getIntent();
                data.putExtra("new_person", p);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        Person p = (Person) getIntent().getSerializableExtra("updated");

        if (p != null) {
            edt_name.setText(p.getName());
            edt_cmt.setText(p.getCmt());
            edt_note.setText(p.getNote());

            if (p.getDegree().equals(Person.COLLEGE_DEGREE)) {
                radio_college.setChecked(true);
            } else if (p.getDegree().equals(Person.UNIVERSITY_DEGREE)) {
                radio_university.setChecked(true);
            } else if (p.getDegree().equals(Person.HIGH_SCHOOL_DEGREE)) {
                radio_highSchool.setChecked(true);
            }

            String[] fav = p.getFav();

            for (String f :
                    fav) {
                cb_readNews.setChecked(f.equals(Person.READ_NEWS));
                cb_readBook.setChecked(f.equals(Person.READ_BOOK));
                cb_code.setChecked(f.equals(Person.CODE));
            }


        }
    }
}
