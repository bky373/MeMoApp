package com.bobo.mytodolist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bobo.mytodolist.databinding.ActivityNoteBinding


// GOT IT

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityNoteBinding>(this, R.layout.activity_note)
    }
}

