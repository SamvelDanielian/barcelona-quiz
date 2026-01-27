package com.example.barcelonaquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionModel implements Parcelable {
    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    private int id;
    private String question, option1, option2, option3, option4;
    private int correctAnsNo;
    private String difficulty;
    private int categoryID;

    public QuestionModel(){}

    public QuestionModel(String question, String option1, String option2, String option3, String option4, int correctAnsNo, String difficulty, int categoryID){
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnsNo = correctAnsNo;
        this.difficulty = difficulty;
        this.categoryID = categoryID;
    }

    protected QuestionModel(Parcel in) {
        id = in.readInt();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        correctAnsNo = in.readInt();
        difficulty = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(correctAnsNo);
        dest.writeString(difficulty);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getCorrectAnsNo() {
        return correctAnsNo;
    }

    public void setCorrectAnsNo(int correctAnsNo) {
        this.correctAnsNo = correctAnsNo;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public static String[] getAllDifficultyLevels() {
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }
}
