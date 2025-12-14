package aman.first.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Quize_time : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quize_time)

        val titlelist = listOf(
            Title("C Programming",R.drawable.c_logo),
            Title("Java Programming",R.drawable.java_logo),
            Title("Python Programming",R.drawable.python_logo),
            Title("C++ Programming",R.drawable.c_add),
            Title("Kotlin Programming",R.drawable.kotlin_logo),
            Title("JavaScript Programming",R.drawable.javascript_logo),
            Title("HTML Programming",R.drawable.html_logo),
            Title("CSS Programming",R.drawable.css_logo),
            Title("PHP Programming",R.drawable.php_logo),
            Title("Swift Programming",R.drawable.swift_logo),
            Title("Android Development",R.drawable.android_logo),
            Title("Flutter Development",R.drawable.flutter_logo)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,2)

        recyclerView.adapter = TitleAdopter(titlelist) { selectedItem ->
            val intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("subject", selectedItem.subject)
            startActivity(intent)
        }
    }
}
