package com.zaibap.quize.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zaibap.quize.R
import kotlinx.android.synthetic.main.activity_score_board.*

class ScoreBoardActivity : AppCompatActivity() {
    private var finalScore=""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)
        if (intent != null) {
            finalScore = intent.getStringExtra("SCORE").toString()
            Log.e("Score","==>Final $finalScore")
        }
        iv_share.setOnClickListener {
            shareApp()
           }
        tv_finalScore.text = "Your Score is $finalScore."
        if (finalScore == "10") {
            tv_scoreMsg.text = "Awesome. You are Genius.\n" +
                    "Congratulations you won the Game"
        }
        else if (finalScore == "5") {
            tv_scoreMsg.text = "You Won!"
        }
       else if (finalScore=="7"){
            tv_scoreMsg.text="You Won! Congratulations"
        }
        else if (finalScore=="9"||finalScore=="9" ){
            tv_scoreMsg.text=" You Won! Congratulations and Well\n" +
                    "Done."
        }
        else if (finalScore=="0" ||finalScore=="1" ||finalScore=="2"  )
        {
            tv_scoreMsg.text="Sorry, You failed"
        }
        else{
            tv_scoreMsg.text ="Well played but you failed. All The\n" +
                    "Best for Next Game"
        }
        cv_playAgainQuiz.setOnClickListener {
            val intent = Intent(this, StartQuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun shareApp() {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Hey i just got $finalScore in this amazing app.trying to check your knowledge and play. ")

            // (Optional) Here we're setting the title of the content
            putExtra(Intent.EXTRA_TITLE, "Introducing content previews")

            // (Optional) Here we're passing a content URI to an image to be displayed
//            data = contentUri
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        startActivity(share)
    }
}