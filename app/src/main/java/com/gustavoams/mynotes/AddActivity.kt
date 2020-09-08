package com.gustavoams.mynotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    val PREFS_FILENAME = "com.gustavoams.mynotes.prefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        getExtra()
        onButton()
    }

    fun getExtra() {
        val note = intent.getStringExtra("note")
        if(note != null && note.isNotEmpty()) editText.setText(note)
    }

    fun onButton() {
        button.setOnClickListener {
            saveData()
            onBackPressed()
        }
    }

    fun saveData () {
        val sharedPref = this.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("note", editText.text.toString())
            commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}