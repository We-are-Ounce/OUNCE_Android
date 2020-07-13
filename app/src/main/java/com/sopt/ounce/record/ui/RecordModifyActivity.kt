package com.sopt.ounce.record.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.activity_record_modify.*

class RecordModifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_modify)
        btn_record_popup.setOnClickListener {
            val popup = PopupMenu(this, btn_record_popup)
            popup.inflate(R.menu.record_popup)
            popup.setOnMenuItemClickListener {
                Toast.makeText(this, it.title,Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

    }


}