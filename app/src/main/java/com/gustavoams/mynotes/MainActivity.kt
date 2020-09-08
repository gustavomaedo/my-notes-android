package com.gustavoams.mynotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val PREFS_FILENAME = "com.gustavoams.mynotes.prefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData()
        onButton()
    }

    private fun setData() {
        var note = getNote()

        if(note.isNotEmpty()) {
            textView.text = note
        } else {
            textView.text = getString(R.string.empty)
        }
    }

    private fun onButton() {
        floatingActionButton.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        val intent = Intent(this, AddActivity::class.java).apply {
            putExtra("note", getNote())
        }
        startActivityForResult(intent, 0)
    }

    private fun getNote() : String {
        val sharedPref = this.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val note = sharedPref!!.getString("note", "")
        return if(note != null && note.isNotEmpty()) {
            note
        } else {
            ""
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            setData()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}