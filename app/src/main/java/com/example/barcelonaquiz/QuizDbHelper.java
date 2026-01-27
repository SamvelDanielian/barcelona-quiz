package com.example.barcelonaquiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.barcelonaquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 2;

    private static QuizDbHelper instance;
    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if(instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "Create Table " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "Create Table " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
            db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("UEFA Champions League");
        insertCategory(c1);
        Category c2 = new Category("UEFA Europe League");
        insertCategory(c2);
        Category c3 = new Category("UEFA Super Cup");
        insertCategory(c3);
        Category c4 = new Category("FIFA Club World Cup");
        insertCategory(c4);
        Category c5 = new Category("La Liga");
        insertCategory(c5);
        Category c6 = new Category("Spanish Cup");
        insertCategory(c6);
        Category c7 = new Category("Spanish Super Cup");
        insertCategory(c7);
        Category c8 = new Category("History");
        insertCategory(c8);
        Category c9 = new Category("Stadium");
        insertCategory(c9);
        Category c10 = new Category("Records");
        insertCategory(c10);
    }

    public void addCategory(Category category){
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories){
        db = getWritableDatabase();

        for(Category category: categories){
            insertCategory(category);
        }
    }

    private void insertCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable(){
        QuestionModel q1 = new QuestionModel("How many Champions League does Barcelona have?", "3", "4", "5", "6", 3, QuestionModel.DIFFICULTY_EASY, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q1);
        QuestionModel q2 = new QuestionModel("When was the Barcelona team founded?", "November 29, 1899", "November 29, 1901", "December 29, 1899", "December 29, 1901", 1, QuestionModel.DIFFICULTY_EASY, Category.HISTORY);
        insertQuestion(q2);
        QuestionModel q3 = new QuestionModel("What is the official name of Barcelona's stadium?", "Know Camp", "Spotify", "Spotify Camp Know", "Spotify Camp Nou", 4, QuestionModel.DIFFICULTY_EASY, Category.STADIUM);
        insertQuestion(q3);
        QuestionModel q4 = new QuestionModel("Who is the Barcelona all-time top scorer?", "Cesar Rodriguez", "Lionel Messi", "Luis Suarez", "Ronaldinho", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q4);
        QuestionModel q5 = new QuestionModel("How many goals has Lionel Messi scored for FC Barcelona in all competitions?", "800", "672", "676", "801", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q5);
        QuestionModel q6 = new QuestionModel("Who is the first Barcelona player to win the Ballon d'Or?", "Luis Suarez", "Lionel Messi", "Johan Cruyff", "Ronaldinho", 1, QuestionModel.DIFFICULTY_HARD, Category.RECORDS);
        insertQuestion(q6);
        QuestionModel q7 = new QuestionModel("Who scored the winning goal that gave Barcelona their first Champions League trophy in 1992?", "Pep Guardiola", "Ronald Koeman", "Hristo Stoichkov", "Eusebio Sacristan", 2, QuestionModel.DIFFICULTY_HARD, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q7);
        QuestionModel q8 = new QuestionModel("How many goals scored Barcelona's top scorer in Champions League for Barcelona?", "129", "125", "120", "130", 3, QuestionModel.DIFFICULTY_HARD, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q8);
        QuestionModel q9 = new QuestionModel("Who are the goalscorers in the 2009 Champions League final?", "Henry and Messi", "Eto'o and Messi", "Eto'o and Henry", "Xavi and Messi", 2, QuestionModel.DIFFICULTY_HARD, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q9);
        QuestionModel q10 = new QuestionModel("When was the last time Barcelona won the Champions League", "2011", "2019", "2009", "2015", 4, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q10);
        QuestionModel q11 = new QuestionModel("Who did Barcelona play in the final when they won the 2006 Champions League", "Arsenal", "Villarreal", "Manchester United", "AC Milan", 1, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q11);
        QuestionModel q12 = new QuestionModel("How many Champions League trophies do Xavi and Iniesta have?", "5", "4", "3", "2", 2, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q12);
        QuestionModel q13 = new QuestionModel("How many Champions League trophy does Neymar Jr have with Barcelona?", "2", "3", "1", "4", 3, QuestionModel.DIFFICULTY_EASY, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q13);
        QuestionModel q14 = new QuestionModel("Who opened the score in the 2015 Champions League final?", "Rakitic", "Iniesta", "Suarez", "Neymar Jr", 1, QuestionModel.DIFFICULTY_EASY, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q14);
        QuestionModel q15 = new QuestionModel("Who was the goalkeeper of Barcelona in the 2011 Champions League final?", "Manuel Neuer", "Victor Valdes", "Gianluigi Donnarumma", "Iker Casillas", 2, QuestionModel.DIFFICULTY_EASY, Category.UEFA_CHAMPIONS_LEAGUE);
        insertQuestion(q15);
        QuestionModel q16 = new QuestionModel("When was the Barcelona's first appearance in the Europa League?", "1964-65", "1968-1969", "1972-73", "1976-77", 3, QuestionModel.DIFFICULTY_HARD, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q16);
        QuestionModel q17 = new QuestionModel("Who knocked out Barcelona in the 2003-04 Europa League?", "Celtic", "Villarreal", "Porto", "Manchester United", 1, QuestionModel.DIFFICULTY_HARD, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q17);
        QuestionModel q18 = new QuestionModel("When was the last time Barcelona played in the Europa League?", "2022-23", "2021-22", "2015-16", "2018-19", 1, QuestionModel.DIFFICULTY_EASY, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q18);
        QuestionModel q19 = new QuestionModel("Approximately how many fans were in the Nou Camp when Barcelona was knocked out from the Europa League in 2022?", "74000-75000", "79000-80000", "85000-86000", "90000-91000", 2, QuestionModel.DIFFICULTY_HARD, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q19);
        QuestionModel q20 = new QuestionModel("How many Europa League titles does Barcelona have?", "0", "1", "2", "3", 1, QuestionModel.DIFFICULTY_EASY, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q20);
        QuestionModel q21 = new QuestionModel("How many seasons has Barcelona played in the Europa League since 2010", "2", "4", "6", "8", 1, QuestionModel.DIFFICULTY_EASY, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q21);
        QuestionModel q22 = new QuestionModel("What is the furthest stage Barcelona reached in the Europa League?", "Final", "Semi-final", "Quarter-final", "Round 16", 2, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q22);
        QuestionModel q23 = new QuestionModel("Who is Barcelona's Europa League top scorer?", "Rexach", "Asensi", "Cruyff", "Neeskens", 3, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q23);
        QuestionModel q24 = new QuestionModel("Who has the most appearances among Barcelona players in the Europa League?", "Migueli", "Asensi", "Cruyff", "Neeskens", 1, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_EUROPE_LEAGUE);
        insertQuestion(q24);
        QuestionModel q25 = new QuestionModel("How many La Liga titles has Barcelona?", "20", "26", "32", "38", 2, QuestionModel.DIFFICULTY_EASY, Category.LA_LIGA);
        insertQuestion(q25);
        QuestionModel q26 = new QuestionModel("When was the last time Barcelona won La Liga?", "2019-2020", "2018-2019", "1992-93", "2000-01", 2, QuestionModel.DIFFICULTY_EASY, Category.LA_LIGA);
        insertQuestion(q26);
        QuestionModel q27 = new QuestionModel("Who is Barcelona's current top scorer in La Liga?", "Pedri", "Raphinha", "Lewandowski", "Messi", 3 , QuestionModel.DIFFICULTY_EASY, Category.LA_LIGA);
        insertQuestion(q27);
        QuestionModel q28 = new QuestionModel("How many points did Barcelona have in 2012-13 season in La Liga?", "98", "100", "109", "112", 2, QuestionModel.DIFFICULTY_MEDIUM, Category.LA_LIGA);
        insertQuestion(q28);
        QuestionModel q29 = new QuestionModel("How many La Ligas has Barcelona won from 2008-09 season?", "8", "9", "10", "11", 1, QuestionModel.DIFFICULTY_MEDIUM, Category.LA_LIGA);
        insertQuestion(q29);
        QuestionModel q30 = new QuestionModel("How many La Ligas has Xavi won with Barcelona?", "5", "8", "10", "11", 2, QuestionModel.DIFFICULTY_MEDIUM, Category.LA_LIGA);
        insertQuestion(q30);
        QuestionModel q31 = new QuestionModel("How many La Ligas has Cruyff won with Barcelona?", "1", "2", "3", "4", 1, QuestionModel.DIFFICULTY_HARD, Category.LA_LIGA);
        insertQuestion(q31);
        QuestionModel q32 = new QuestionModel("How many La Liga titles has Pep Guardiola won as a manager?", "3", "4", "5", "6", 1, QuestionModel.DIFFICULTY_HARD, Category.LA_LIGA);
        insertQuestion(q32);
        QuestionModel q33 = new QuestionModel("How many official games did Ronaldo play for Barcelona?", "46", "47", "48", "49", 4, QuestionModel.DIFFICULTY_HARD, Category.LA_LIGA);
        insertQuestion(q33);
        QuestionModel q34 = new QuestionModel("How many UEFA Super Cup titles has Barcelona won?", "2", "3", "4", "5", 4, QuestionModel.DIFFICULTY_EASY, Category.UEFA_SUPER_CUP);
        insertQuestion(q34);
        QuestionModel q35 = new QuestionModel("When was the last time Barcelona won UEFA Super Cup?", "2013", "2014", "2015", "2022", 3, QuestionModel.DIFFICULTY_MEDIUM, Category.UEFA_SUPER_CUP);
        insertQuestion(q35);
        QuestionModel q36 = new QuestionModel("How many UEFA Super Cup finals has Barcelona played?", "7", "8", "9", "10", 3, QuestionModel.DIFFICULTY_HARD, Category.UEFA_SUPER_CUP);
        insertQuestion(q36);
        QuestionModel q37 = new QuestionModel("How many Club World Cup titles has Barcelona won?", "2", "3", "6", "7", 2, QuestionModel.DIFFICULTY_EASY, Category.FIFA_CLUB_WORLD_CUP);
        insertQuestion(q37);
        QuestionModel q38 = new QuestionModel("In which of the following years did Barcelona not win FIFA Club World Cup?", "2006", "2009", "2011", "2015", 1, QuestionModel.DIFFICULTY_MEDIUM, Category.FIFA_CLUB_WORLD_CUP);
        insertQuestion(q38);
        QuestionModel q39 = new QuestionModel("Who did Barcelona play against in the 2015 FIFA Club World Cup?", "River PLate", "Al Hilal", "Flamengo", "Santos FC", 1, QuestionModel.DIFFICULTY_HARD, Category.FIFA_CLUB_WORLD_CUP);
        insertQuestion(q39);
        QuestionModel q40 = new QuestionModel("How many Spanish Cup has Barcelona won?", "30", "31", "32", "33", 2, QuestionModel.DIFFICULTY_EASY, Category.SPANISH_CUP);
        insertQuestion(q40);
        QuestionModel q41 = new QuestionModel("When was the last time Barcelona won Copa del Rey?", "2020-21", "2009-10", "1966-67", "2021-22", 1, QuestionModel.DIFFICULTY_MEDIUM, Category.SPANISH_CUP);
        insertQuestion(q41);
        QuestionModel q42 = new QuestionModel("Which player has won the most Copa del Rey?", "Pique, Messi and Busquets", "Pique, Messi and Roberto", "Pique, Busquets and Roberto", "Messi, Busquets and Roberto", 1, QuestionModel.DIFFICULTY_HARD, Category.SPANISH_CUP);
        insertQuestion(q42);
        QuestionModel q43 = new QuestionModel("How many Spanish Super Cups does Barcelona have?", "12", "14", "16", "18", 2, QuestionModel.DIFFICULTY_EASY, Category.SPANISH_SUPER_CUP);
        insertQuestion(q43);
        QuestionModel q44 = new QuestionModel("When was the last time Barcelona won Spanish Super Cup?", "2023", "2022", "2021", "2020", 1, QuestionModel.DIFFICULTY_MEDIUM, Category.SPANISH_SUPER_CUP);
        insertQuestion(q44);
        QuestionModel q45 = new QuestionModel("Who has won Man of the Match award in 2023 Spanish Super Cup final", "Pedri", "Gavi", "Lewandowski", "Benzema", 2, QuestionModel.DIFFICULTY_HARD, Category.SPANISH_SUPER_CUP);
        insertQuestion(q45);
        QuestionModel q46 = new QuestionModel("How many trophies did the team win under Pep Guardiola?", "12", "14", "15", "21", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q46);
        QuestionModel q47 = new QuestionModel("How many years did Pep Guardiola spend as the head coach of Barcelona?", "3", "9", "12", "4", 4, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q47);
        QuestionModel q48 = new QuestionModel("When did the first elected president of Barcelona assume office?", "1971", "1974", "1978", "1988", 3, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q48);
        QuestionModel q49 = new QuestionModel("When did Barcelona win its first European Cup Winners' Cup?", "16 May 1979", "22 June 1987", "10 May 1974", "11 March 1988", 1, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q49);
        QuestionModel q50 = new QuestionModel("When did Diego Maradona join Barcelona?", "May 1981", "June 1982", "July 1983", "August 1984", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q50);
        QuestionModel q51 = new QuestionModel("In what year did Ronaldinho join Barcelona?", "2000", "2003", "2005", "1999", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q51);
        QuestionModel q52 = new QuestionModel("In what year did Barcelona win its first La Liga title?", "1921", "1924", "1927", "1929", 4, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q52);
        QuestionModel q53 = new QuestionModel("How many games did Andres Iniesta play for Barcelona in all competitions?", "444", "505", "674", "767", 3, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q53);
        QuestionModel q54 = new QuestionModel("How many games did Xavi play in all competitions for Barcelona?", "336", "458", "613", "767", 4, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q54);
        QuestionModel q55 = new QuestionModel("How many years did Jack Greenwell use as Barcelona's longest serving manager?", "8", "9", "10", "11", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q55);
        QuestionModel q56 = new QuestionModel("What Jersey Number does David Villa wear for Barcelona?", "7", "9", "11", "17", 1, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q56);
        QuestionModel q57 = new QuestionModel("What Jersey Number did Gerrard Pique wear for Barcelona?", "2", "3", "4", "23", 2, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q57);
        QuestionModel q58 = new QuestionModel("What position does Romario play for Barcelona?", "Defence", "Midfielder", "Forward", "Manager", 3, QuestionModel.DIFFICULTY_EASY, Category.RECORDS);
        insertQuestion(q58);
    }

    public void addQuestion(QuestionModel questionModel){
        db = getWritableDatabase();
        insertQuestion(questionModel);
    }

    public void addQuestions(List<QuestionModel> questionModels){
        db = getWritableDatabase();

        for(QuestionModel questionModel: questionModels){
            insertQuestion(questionModel);
        }
    }

    private void insertQuestion(QuestionModel questionModel){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, questionModel.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, questionModel.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, questionModel.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, questionModel.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, questionModel.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, questionModel.getCorrectAnsNo());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, questionModel.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, questionModel.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while(c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    @SuppressLint("Range")
    public ArrayList<QuestionModel> getAllQuestions(){
        ArrayList<QuestionModel> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                QuestionModel question = new QuestionModel();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setCorrectAnsNo(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public ArrayList<QuestionModel> getQuestions(int categoryID, String difficulty){
        ArrayList<QuestionModel> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(QuestionsTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if(c.moveToFirst()){
            do {
                QuestionModel question = new QuestionModel();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setCorrectAnsNo(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
