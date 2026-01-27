package com.example.barcelonaquiz;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuizLogicTest {

    @Test
    public void calculateScoreIfCorrectlyAnswered() {
        int result = QuizLogic.calculateScore(3, true);
        assertEquals(4, result);
    }

    @Test
    public void calculateScoreIfIncorrectlyAnswered() {
        int result = QuizLogic.calculateScore(3, false);
        assertEquals(3, result);
    }
}