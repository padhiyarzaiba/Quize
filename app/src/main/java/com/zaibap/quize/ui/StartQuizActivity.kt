package com.zaibap.quize.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zaibap.quize.R


import com.zaibap.quize.database.databaseHelper
import com.zaibap.quize.model.QuestionModel
import java.util.*
import java.util.Collections.shuffle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start_quiz.*
import android.widget.RadioButton
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.CountDownTimer
import android.util.Log
import java.lang.String


class StartQuizActivity : AppCompatActivity() {
    private var questionList: List<QuestionModel>? = null
    var questionCountTotal = 0
    private var questionCounter = 0
    private var score = 0
    private var answered = false
    private var textColorDefaultCd: ColorStateList? = null
    val EXTRA_SCORE = "extraScore"
    private var backPressedTime: Long = 0

    private var countDownTimer: CountDownTimer? = null
    private val COUNTDOWN_IN_MILLIS: Long = 20000
    private var timeLeftInMillis: Long = 0
    private var currentQuestion: QuestionModel? = null
    private var textColorDefaultRb: ColorStateList? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_quiz)

        textColorDefaultRb = radio_option1.textColors
        textColorDefaultCd = tv_countdown.textColors
        val dbHelper = databaseHelper(this)
        questionList = dbHelper.getAllQuestions()
        questionCountTotal = questionList!!.size
        shuffle(questionList)
        showNextQuestion()
        btn_confirm_next.setOnClickListener {
            if (!answered) {
                if (radio_option1.isChecked || radio_option2.isChecked || radio_option3.isChecked) {
                    checkAnswer()

                } else {
                    Toast.makeText(
                        this,
                        "Please select an answer",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                showNextQuestion()
            }
        }

    }

    fun showNextQuestion() {
        radio_option1.setTextColor(textColorDefaultRb);
        radio_option2.setTextColor(textColorDefaultRb);
        radio_option3.setTextColor(textColorDefaultRb);
        radio_group.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList!![questionCounter];

            tv_question.text = currentQuestion!!.getQuestion();
            radio_option1.text = currentQuestion!!.getOption1();
            radio_option2.text = currentQuestion!!.getOption2();
            radio_option3.text = currentQuestion!!.getOption3();

            questionCounter++;
            tv_question_count.text = "Question: $questionCounter/$questionCountTotal";
            answered = false;
            btn_confirm_next.text = "Confirm"

            timeLeftInMillis = COUNTDOWN_IN_MILLIS
            startCountDown()
        } else {
            Toast.makeText(this, "Congratlation quize complete ", Toast.LENGTH_SHORT).show()
            Log.e("Score", "==>$score")
            val intent = Intent(this, ScoreBoardActivity::class.java)
            intent.putExtra("SCORE", score.toString())
            startActivity(intent)
//            finish();
        }
    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountDownText()
                checkAnswer()
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000).toInt() / 60
        val seconds = (timeLeftInMillis / 1000).toInt() % 60
        val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        tv_countdown.text = timeFormatted

        if (timeLeftInMillis < 10000) {
            tv_countdown.setTextColor(Color.RED)
        } else {
            tv_countdown.setTextColor(textColorDefaultCd)
        }
    }

    private fun checkAnswer() {
        answered = true
        countDownTimer!!.cancel()
        val rbSelected: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        val answerNr: Int = radio_group.indexOfChild(rbSelected) + 1
        if (answerNr == currentQuestion!!.getAnswerNr()) {
            score++
            tv_score.text = "Score: $score"
        }
        showSolution()
    }

    private fun showSolution() {
        radio_option1.setTextColor(Color.RED)
        radio_option2.setTextColor(Color.RED)
        radio_option3.setTextColor(Color.RED)

        when (currentQuestion!!.getAnswerNr()) {
            1 -> {
                radio_option1.setTextColor(Color.GREEN)
//                tv_question.text = "Answer 1 is correct"
            }
            2 -> {
                radio_option2.setTextColor(Color.GREEN)
//                tv_question.text = "Answer 2 is correct"
            }
            3 -> {
                radio_option3.setTextColor(Color.GREEN)
//                tv_question.text = "Answer 3 is correct"
            }
        }

        if (questionCounter < questionCountTotal) {
            btn_confirm_next.text = "Next"
        } else {
            btn_confirm_next.text = "Finish"

        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish()
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }


}