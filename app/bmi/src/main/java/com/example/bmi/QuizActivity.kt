package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.widget.*
import java.lang.Exception
import java.util.ArrayList

/**
 * Healthy Quiz
 *
 * @author Rafał Sochacki
 */
class QuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 1
    private var questionCounter = 0
    private var currentScore = 0
    private var textViewCurrentQuestion: TextView? = null
    private var textViewResult: TextView? = null
    private var textViewCurrentScoreValue: TextView? = null
    private var textViewQuestionCounterValue: TextView? = null
    private var buttonMain: Button? = null
    private var buttonAnswer1: Button? = null
    private var buttonAnswer2: Button? = null
    private var buttonAnswer3: Button? = null
    private var buttonAnswer4: Button? = null
    private var buttonRestartQuiz: Button? = null
    private var questions: ArrayList<Question>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        buttonAnswer1 = findViewById(R.id.buttonAnswer1)
        buttonAnswer2 = findViewById(R.id.buttonAnswer2)
        buttonAnswer3 = findViewById(R.id.buttonAnswer3)
        buttonAnswer4 = findViewById(R.id.buttonAnswer4)
        buttonRestartQuiz = findViewById(R.id.buttonRestartQuiz)
        buttonMain = findViewById(R.id.backToMainMenu)
        textViewCurrentQuestion = findViewById(R.id.textViewCurrentQuestion)
        textViewResult = findViewById(R.id.textViewResult)
        textViewCurrentScoreValue = findViewById(R.id.textViewCurrentScoreValue)
        textViewQuestionCounterValue = findViewById(R.id.textViewQuestionCounterValue)
        buttonAnswer1.setOnClickListener(answerButtonListener)
        buttonAnswer2.setOnClickListener(answerButtonListener)
        buttonAnswer3.setOnClickListener(answerButtonListener)
        buttonAnswer4.setOnClickListener(answerButtonListener)
        buttonRestartQuiz.setOnClickListener(restartButtonListener)
        generateView()
        buttonMain.setOnClickListener(View.OnClickListener { finish() })
    }

    fun generateView() {
        loadQuestions()
        questionCounter = questions!!.size
        restartQuiz()
    }

    private val answerButtonListener = View.OnClickListener { view ->
        try {
            if (currentQuestionIndex > questionCounter) {
                return@OnClickListener
            }
            var choosenAnswer = -1
            when (view.id) {
                R.id.buttonAnswer1 -> choosenAnswer = 1
                R.id.buttonAnswer2 -> choosenAnswer = 2
                R.id.buttonAnswer3 -> choosenAnswer = 3
                R.id.buttonAnswer4 -> choosenAnswer = 4
            }
            checkAnswer(choosenAnswer)
            updateScore()
            currentQuestionIndex++
            if (currentQuestionIndex > questionCounter) {
                textViewCurrentQuestion!!.text = getString(R.string.end)
                buttonAnswer1!!.visibility = View.INVISIBLE
                buttonAnswer2!!.visibility = View.INVISIBLE
                buttonAnswer3!!.visibility = View.INVISIBLE
                buttonAnswer4!!.visibility = View.INVISIBLE
                return@OnClickListener
            }
            updateQuestion()
        } catch (e: Exception) {
            textViewCurrentQuestion!!.setText(R.string.quiz_error)
        }
    }

    private fun checkAnswer(answer: Int) {
        val currentQuestion = questions!![currentQuestionIndex - 1]
        val correctAnswer = currentQuestion.getAnswers()[currentQuestion.getCorrectAnswer() - 1]
        if (answer == currentQuestion.getCorrectAnswer()) {
            currentScore++
            textViewResult!!.text = getString(R.string.good_answer)
            textViewResult!!.setTextColor(Color.GREEN)
        } else {
            textViewResult!!.text = getString(R.string.correct_answer) + " " + correctAnswer
            textViewResult!!.setTextColor(Color.RED)
        }
        val handler = Handler()
        handler.postDelayed({ textViewResult!!.text = "" }, 2000)
    }

    private val restartButtonListener = View.OnClickListener {
        try {
            restartQuiz()
        } catch (e: Exception) {
            textViewCurrentQuestion!!.setText(R.string.quiz_problem)
        }
    }

    private fun loadQuestions() {
        questions = ArrayList()
        questions!!.add(Question(getString(R.string.question_1), arrayOf<String?>(getString(R.string.question_1_ans1), getString(R.string.question_1_ans2), getString(R.string.question_1_ans3), getString(R.string.question_1_ans4)), 3))
        questions!!.add(Question(getString(R.string.question_2), arrayOf<String?>(getString(R.string.question_2_ans1), getString(R.string.question_2_ans2), getString(R.string.question_2_ans3), getString(R.string.question_2_ans4)), 2))
        questions!!.add(Question(getString(R.string.question_3), arrayOf<String?>(getString(R.string.question_3_ans1), getString(R.string.question_3_ans2), getString(R.string.question_3_ans3), getString(R.string.question_3_ans4)), 4))
        questions!!.add(Question(getString(R.string.question_4), arrayOf<String?>(getString(R.string.question_4_ans1), getString(R.string.question_4_ans2), getString(R.string.question_4_ans3), getString(R.string.question_4_ans4)), 1))
        questions!!.add(Question(getString(R.string.question_5), arrayOf<String?>(getString(R.string.question_5_ans1), getString(R.string.question_5_ans2), getString(R.string.question_5_ans3), getString(R.string.question_5_ans4)), 1))
        questions!!.add(Question(getString(R.string.question_6), arrayOf<String?>(getString(R.string.question_6_ans1), getString(R.string.question_6_ans2), getString(R.string.question_6_ans3), getString(R.string.question_6_ans4)), 4))
        questions!!.add(Question(getString(R.string.question_7), arrayOf<String?>(getString(R.string.question_7_ans1), getString(R.string.question_7_ans2), getString(R.string.question_7_ans3), getString(R.string.question_7_ans4)), 3))
    }

    private fun updateScore() {
        textViewCurrentScoreValue!!.text = currentScore.toString()
    }

    private fun updateQuestion() {
        textViewQuestionCounterValue!!.text = "$currentQuestionIndex/$questionCounter"
        updateScore()
        val currentQuestion = questions!![currentQuestionIndex - 1]
        val currentAnswers = currentQuestion.getAnswers()
        textViewCurrentQuestion.setText(currentQuestion.getQuestion())
        buttonAnswer1!!.text = currentAnswers!![0]
        buttonAnswer2!!.text = currentAnswers!![1]
        buttonAnswer3!!.text = currentAnswers!![2]
        buttonAnswer4!!.text = currentAnswers!![3]
    }

    private fun restartQuiz() {
        currentScore = 0
        currentQuestionIndex = 1
        buttonAnswer1!!.visibility = View.VISIBLE
        buttonAnswer2!!.visibility = View.VISIBLE
        buttonAnswer3!!.visibility = View.VISIBLE
        buttonAnswer4!!.visibility = View.VISIBLE
        updateQuestion()
    }
}