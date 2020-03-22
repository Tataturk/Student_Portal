package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_portal.*

const val EXTRA_PORTAL = "EXTRA_PORtAL"

class CreatePortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create a portal!"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_portal)
        initViews()
    }

    private fun initViews() {
        btn_confirm.setOnClickListener { onSaveClick() }
    }

    private fun onSaveClick()   {
        if (ti_title.text.toString().isNotBlank())  {
            val portal = Portal(
                ti_title.text.toString(),
                ti_uri.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL,portal)
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        } else {
            Toast.makeText(this,"Fields cannot be empty",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
