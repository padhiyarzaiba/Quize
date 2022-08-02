package com.zaibap.quize.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor

import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper
import com.zaibap.quize.database.quizConts.*

import com.zaibap.quize.model.QuestionModel
import com.zaibap.quize.database.quizConts.QuestionsTable


import com.zaibap.quize.model.Question








class databaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private var db: SQLiteDatabase? = null
    override fun onCreate(db: SQLiteDatabase) {
        this.db = db
        val SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")"
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE)

        fillQuestionsTable()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME)
        onCreate(db)
    }

    private fun fillQuestionsTable() {
        val q1  = QuestionModel("Who is the Prime Minister of India?", "Narendra Modi", "Rahul Gandhi", "Manmohan Singh", 1)
        addQuestion(q1)
        val q2 = QuestionModel("What is the capital of India?", "Mumbai", "Chennai", "Delhi", 3)
        addQuestion(q2)
        val q3 = QuestionModel("What is sum of 15 + 25 ?", "5", "25", "40", 3)
        addQuestion(q3)
        val q4 = QuestionModel("Which one is maximum? 25, 11, 17, 18, 40, 42", "11", "42", "17", 2)
        addQuestion(q4)
        val q5 = QuestionModel("What is the official language of Gujarat?", "Hindi", "Gujarati", "Marathi", 2)
        addQuestion(q5)
        val q6 = QuestionModel("What is multiplication of 12 * 12 ?", "124", "12", "none", 3)
        addQuestion(q6)
        val q7 = QuestionModel("Which state of India has the largest population?", "UP", "Bihar", "Gujarat", 1)
        addQuestion(q7)
        val q8 = QuestionModel("Who is the Home Minister of India?", "Amit Shah", "Rajnath Singh", " Narendra Modi,", 1)
        addQuestion(q8)
        val q9 = QuestionModel("Which country is located in Asia?", "India", "USA", "UK", 1)
        addQuestion(q9)
        val q10 = QuestionModel("Which language(s) is/are used for Android app development?", "Java", ", Java & Kotlin", "Swift", 2)
        addQuestion(q10)
    }

    private fun addQuestion(question: QuestionModel) {
        val cv = ContentValues()
        cv.put(QuestionsTable.COLUMN_QUESTION, question.question)
        cv.put(QuestionsTable.COLUMN_OPTION1, question.option1)
        cv.put(QuestionsTable.COLUMN_OPTION2, question.option2)
        cv.put(QuestionsTable.COLUMN_OPTION3, question.option3)
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.answerNr)
        db!!.insert(QuestionsTable.TABLE_NAME, null, cv)
    }


    @SuppressLint("Range")
    fun getAllQuestions(): List<QuestionModel>? {
        val questionList: MutableList<QuestionModel> = ArrayList()
        db = readableDatabase
        val c= db!!.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null)

        if (c.moveToFirst()) {
            do {

                val question = QuestionModel()
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)))
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)))
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)))
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)))
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)))
                questionList.add(question)
            } while (c.moveToNext())
        }
        c.close()
        return questionList
    }

    companion object {
        private const val DATABASE_NAME = "Quiz.db"
        private const val DATABASE_VERSION = 2
    }
}