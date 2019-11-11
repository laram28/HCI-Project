package com.example.hci_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Math_Activity extends AppCompatActivity {

    Button b_continue;
    TextView math_Question;
    EditText math_Answer;

    List<Item> questions;
    int curQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_);

        b_continue = (Button) findViewById(R.id.b_continue);
        math_Question = (TextView) findViewById(R.id.math_Question);
        math_Answer = (EditText) findViewById(R.id.math_Answer);

        b_continue.setVisibility(View.INVISIBLE);

        questions = new ArrayList<>();
        //add all questions and answers
        for(int i = 0; i < Database.questions.length; i++){
            questions.add(new Item(Database.questions[i], Database.answers[i]));
        }

        //shuffle questions
        Collections.shuffle(questions);

        math_Question.setText(questions.get(curQuestion).getQuestion());

        math_Answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //check if the answer is right
                if(math_Answer.getText().toString().equalsIgnoreCase(questions.get(curQuestion).getAnswer())){
                    b_continue.setVisibility(View.VISIBLE);
                }
                else {
                    b_continue.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        b_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curQuestion < (Database.questions.length - 1)) {
                    //next question
                    curQuestion++;
                    math_Question.setText(questions.get(curQuestion).getQuestion());
                    b_continue.setVisibility(View.INVISIBLE);
                    math_Answer.setText("");
                }
                else{
                    //no more questions
                    Toast.makeText(Math_Activity.this, "Great Job!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void buttonOnClick (View v) {
        Intent math = new Intent(Math_Activity.this, MainActivity.class);
        startActivity(math);
    }
}
