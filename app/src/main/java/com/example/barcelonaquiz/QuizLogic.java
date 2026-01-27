package com.example.barcelonaquiz;

public class QuizLogic {

    public static int calculateScore(int currentScore, boolean isCorrect) {
        if (isCorrect) {
            return currentScore + 1;
        }
        return currentScore;
    }
}