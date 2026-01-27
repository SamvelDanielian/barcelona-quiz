package com.example.barcelonaquiz;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionModelsTest {

    @Test
    public void checkIfQuestionModelsGettersWorkCorrectly() {
        QuestionModel question = new QuestionModel(
                "Who won the Champions League in 2015?",
                "Barcelona",
                "Juventus",
                "Bayern",
                "Real Madrid",
                1,
                "Easy",
                1
        );

        assertEquals("Who won the Champions League in 2015?", question.getQuestion());
        assertEquals("Barcelona", question.getOption1());
        assertEquals("Juventus", question.getOption2());
        assertEquals("Bayern", question.getOption3());
        assertEquals("Real Madrid", question.getOption4());
        assertEquals(1, question.getCorrectAnsNo());
        assertEquals("Easy", question.getDifficulty());
        assertEquals(1, question.getCategoryID());
    }

    @Test
    public void checkIfQuestionModelsSettersWorkCorrectly() {
        QuestionModel question = new QuestionModel();

        question.setQuestion("Who is Barça's all-time top scorer?");
        question.setOption1("Xavi");
        question.setOption2("Ronaldinho");
        question.setOption3("Messi");
        question.setOption4("Lewandowski");
        question.setDifficulty("Easy");
        question.setCategoryID(10);
        question.setCorrectAnsNo(3);

        assertEquals("Who is Barça's all-time top scorer?", question.getQuestion());
        assertEquals("Xavi", question.getOption1());
        assertEquals("Ronaldinho", question.getOption2());
        assertEquals("Messi", question.getOption3());
        assertEquals("Lewandowski", question.getOption4());
        assertEquals("Easy", question.getDifficulty());
        assertEquals(10, question.getCategoryID());
        assertEquals(3, question.getCorrectAnsNo());
    }
}