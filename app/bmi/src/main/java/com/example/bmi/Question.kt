package com.example.bmi

class Question(question: String, answers: Array<String?>, correctAnswer: Int) {
    var question = ""
    var answers: Array<String?>
    var correctAnswer = -1

    init {
        this.question = question
        this.answers = answers
        this.correctAnswer = correctAnswer
    }
}