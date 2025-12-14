package aman.first.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LevelActivity : AppCompatActivity() {

    private lateinit var btnBeginner: MaterialButton
    private lateinit var btnMedium: MaterialButton
    private lateinit var btnHard: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        btnBeginner = findViewById(R.id.btnBeginner)
        btnMedium = findViewById(R.id.btnMedium)
        btnHard = findViewById(R.id.btnHard)

        val subject = intent.getStringExtra("subject")

        btnBeginner.setOnClickListener {
            startQuiz(subject!!, "beginner")
        }

        btnMedium.setOnClickListener {
            startQuiz(subject!!, "medium")
        }

        btnHard.setOnClickListener {
            startQuiz(subject!!, "hard")
        }
    }

    private fun startQuiz(subject: String, level: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("subject", subject)
        intent.putExtra("level", level)
        startActivity(intent)
    }
}
