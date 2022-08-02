package com.zaibap.quize.database

import android.provider.BaseColumns




class quizConts  {

    private fun quizConts() {}
    object QuestionsTable : BaseColumns {

        const val TABLE_NAME = "quiz_master"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_OPTION1 = "option1"
        const val COLUMN_OPTION2 = "option2"
        const val COLUMN_OPTION3 = "option3"
        const val COLUMN_ANSWER_NR = "answer_nr"
    }
}