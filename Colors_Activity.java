package com.example.hci_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Colors_Activity extends AppCompatActivity {

    private TextView countLabel;
    private ImageView questionImage;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private String rightAns;
    private int rightAnsCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizInfo[][] = {
            //image and answer choices
        {"bluecircle", "blue", "red", "green", "yellow"},
        {"greentriangle", "green", "red", "blue", "yellow"},
        {"redsquare", "red", "yellow", "green", "blue"},
        {"yellowrectangle", "yellow", "red", "green", "blue"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_);

        countLabel = findViewById(R.id.question_Number);
        questionImage = findViewById(R.id.questionImage);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        //add all questions and answers
        for (int i = 0; i < quizInfo.length; i++) {
            //Prepare array
            ArrayList<String> tempArray = new ArrayList<>();
            tempArray.add(quizInfo[i][0]); //Image Name
            tempArray.add(quizInfo[i][1]); //Right Answer
            tempArray.add(quizInfo[i][2]); //Choice 1
            tempArray.add(quizInfo[i][3]); //Choice 2
            tempArray.add(quizInfo[i][4]); //Choice 3

            //tempArray to quizArray
            quizArray.add(tempArray);

        }
        showNextQuiz();
    }

    public void showNextQuiz() {
        //Update quizCountLabel
        countLabel.setText("Q" + quizCount);

        //Generate random number between 0 and 4 (quizArray's size - 1)
        Random random = new Random();
        int randNum = random.nextInt(quizArray.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randNum);

        //Set Image and right answer
        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        rightAns = quiz.get(1);

        //Remove Image Name from quiz and shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        //set choices.
        answer1.setText(quiz.get(0));
        answer2.setText(quiz.get(1));
        answer3.setText(quiz.get(2));
        answer4.setText(quiz.get(3));

        //remove this quiz from quizArray
        quizArray.remove(randNum);
    }

    public void checkAnswer(View view) {
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAns)) {
            //Correct!
            alertTitle = "Correct!";
            rightAnsCount++;

        }
        else {
            //Wrong
            alertTitle = "Wrong..";
        }

        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + rightAns);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(quizArray.size() < 1){
                    //quizArray is empty.
                    showResult();
                }
                else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(rightAnsCount + " / 4");
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

    public void buttonOnClick(View v) {
        Intent math = new Intent(Colors_Activity.this, MainActivity.class);
        startActivity(math);
    }
}