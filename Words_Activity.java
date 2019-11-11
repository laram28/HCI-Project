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

public class Words_Activity extends AppCompatActivity {

    Button b_continue;
    TextView word_Question;
    EditText word_Answer;

    List<ItemWords> questions;
    int curQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);

        b_continue = (Button) findViewById(R.id.b_continue);
        word_Question = (TextView) findViewById(R.id.ran_Word);
        word_Answer = (EditText) findViewById(R.id.word_Answer);

        b_continue.setVisibility(View.INVISIBLE);

        questions = new ArrayList<>();
        //add all questions and answers
        for(int i = 0; i < DatabaseWords.questions.length; i++){
            questions.add(new ItemWords(DatabaseWords.questions[i], DatabaseWords.answers[i]));
        }

        //shuffle questions
        Collections.shuffle(questions);

        word_Question.setText(questions.get(curQuestion).getQuestion());

        word_Answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //check if the answer is right
                if(word_Answer.getText().toString().equalsIgnoreCase(questions.get(curQuestion).getAnswer())){
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
                if(curQuestion < (DatabaseWords.questions.length - 1)) {
                    //next question
                    curQuestion++;
                    word_Question.setText(questions.get(curQuestion).getQuestion());
                    b_continue.setVisibility(View.INVISIBLE);
                    word_Answer.setText("");
                }
                else{
                    //no more questions
                    Toast.makeText(Words_Activity.this, "Great Job!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void buttonOnClick (View v) {
        Intent words = new Intent(Words_Activity.this, MainActivity.class);
        startActivity(words);
    }
}
