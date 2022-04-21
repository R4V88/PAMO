package com.example.bmi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Healthy Quiz
 *
 * @author Rafał Sochacki
 */

public class QuizActivity extends AppCompatActivity {
    private int currentQuestionIndex = 1;
    private int questionCounter = 0;
    private int currentScore = 0;

    private TextView textViewCurrentQuestion, textViewResult, textViewCurrentScoreValue,
            textViewQuestionCounterValue;
    private Button buttonMain, buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4,
            buttonRestartQuiz;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);
        buttonRestartQuiz = findViewById(R.id.buttonRestartQuiz);
        buttonMain = findViewById(R.id.backToMainMenu);
        textViewCurrentQuestion = findViewById(R.id.textViewCurrentQuestion);
        textViewResult = findViewById(R.id.textViewResult);
        textViewCurrentScoreValue = findViewById(R.id.textViewCurrentScoreValue);
        textViewQuestionCounterValue = findViewById(R.id.textViewQuestionCounterValue);

        buttonAnswer1.setOnClickListener(answerButtonListener);
        buttonAnswer2.setOnClickListener(answerButtonListener);
        buttonAnswer3.setOnClickListener(answerButtonListener);
        buttonAnswer4.setOnClickListener(answerButtonListener);
        buttonRestartQuiz.setOnClickListener(restartButtonListener);

        generateView();

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void generateView() {


        loadQuestions();

        questionCounter = questions.size();

        restartQuiz();

    }

    private final View.OnClickListener answerButtonListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            try {
                if (currentQuestionIndex > questionCounter) {
                    return;
                }

                int choosenAnswer = -1;
                switch (view.getId()) {
                    case R.id.buttonAnswer1:
                        choosenAnswer = 1;
                        break;
                    case R.id.buttonAnswer2:
                        choosenAnswer = 2;
                        break;
                    case R.id.buttonAnswer3:
                        choosenAnswer = 3;
                        break;
                    case R.id.buttonAnswer4:
                        choosenAnswer = 4;
                        break;
                }

                checkAnswer(choosenAnswer);
                updateScore();
                currentQuestionIndex++;

                if (currentQuestionIndex > questionCounter) {
                    textViewCurrentQuestion.setText(getString(R.string.end));
                    buttonAnswer1.setVisibility(View.INVISIBLE);
                    buttonAnswer2.setVisibility(View.INVISIBLE);
                    buttonAnswer3.setVisibility(View.INVISIBLE);
                    buttonAnswer4.setVisibility(View.INVISIBLE);
                    return;
                }

                updateQuestion();
            } catch (Exception e) {
                textViewCurrentQuestion.setText(R.string.quiz_error);
            }
        }
    };

    private void checkAnswer(int answer) {
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        String correctAnswer = currentQuestion.getAnswers()[currentQuestion.getCorrectAnswer() - 1];
        if (answer == currentQuestion.getCorrectAnswer()) {
            currentScore++;
            textViewResult.setText(getString(R.string.good_answer));
            textViewResult.setTextColor(Color.GREEN);
        } else {
            textViewResult.setText(getString(R.string.correct_answer).concat(" ").concat(correctAnswer));
            textViewResult.setTextColor(Color.RED);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                textViewResult.setText("");
            }
        }, 2000);
    }

    private final View.OnClickListener restartButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                restartQuiz();
            } catch (Exception e) {
                textViewCurrentQuestion.setText(R.string.quiz_problem);
            }
        }
    };

    private void loadQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question(getString(R.string.question_1), new String[]{getString(R.string.question_1_ans1), getString(R.string.question_1_ans2), getString(R.string.question_1_ans3), getString(R.string.question_1_ans4)}, 3));
        questions.add(new Question(getString(R.string.question_2), new String[]{getString(R.string.question_2_ans1), getString(R.string.question_2_ans2), getString(R.string.question_2_ans3), getString(R.string.question_2_ans4)}, 2));
        questions.add(new Question(getString(R.string.question_3), new String[]{getString(R.string.question_3_ans1), getString(R.string.question_3_ans2), getString(R.string.question_3_ans3), getString(R.string.question_3_ans4)}, 4));
        questions.add(new Question(getString(R.string.question_4), new String[]{getString(R.string.question_4_ans1), getString(R.string.question_4_ans2), getString(R.string.question_4_ans3), getString(R.string.question_4_ans4)}, 1));
        questions.add(new Question(getString(R.string.question_5), new String[]{getString(R.string.question_5_ans1), getString(R.string.question_5_ans2), getString(R.string.question_5_ans3), getString(R.string.question_5_ans4)}, 1));
        questions.add(new Question(getString(R.string.question_6), new String[]{getString(R.string.question_6_ans1), getString(R.string.question_6_ans2), getString(R.string.question_6_ans3), getString(R.string.question_6_ans4)}, 4));
        questions.add(new Question(getString(R.string.question_7), new String[]{getString(R.string.question_7_ans1), getString(R.string.question_7_ans2), getString(R.string.question_7_ans3), getString(R.string.question_7_ans4)}, 3));
    }

    private void updateScore() {
        textViewCurrentScoreValue.setText(String.valueOf(currentScore));
    }

    private void updateQuestion() {
        textViewQuestionCounterValue.setText(String.valueOf(currentQuestionIndex).concat("/").concat(String.valueOf(questionCounter)));
        updateScore();

        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        String[] currentAnswers = currentQuestion.getAnswers();

        textViewCurrentQuestion.setText(currentQuestion.getQuestion());

        buttonAnswer1.setText(currentAnswers[0]);
        buttonAnswer2.setText(currentAnswers[1]);
        buttonAnswer3.setText(currentAnswers[2]);
        buttonAnswer4.setText(currentAnswers[3]);
    }

    private void restartQuiz() {
        currentScore = 0;
        currentQuestionIndex = 1;
        buttonAnswer1.setVisibility(View.VISIBLE);
        buttonAnswer2.setVisibility(View.VISIBLE);
        buttonAnswer3.setVisibility(View.VISIBLE);
        buttonAnswer4.setVisibility(View.VISIBLE);
        updateQuestion();
    }
}
