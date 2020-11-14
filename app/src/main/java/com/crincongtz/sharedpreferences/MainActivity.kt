package com.crincongtz.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

const val MY_COUNTER_KEY = "counter"

class MainActivity : AppCompatActivity() {

    var sharedPreferences: SharedPreferences? = null

    var myTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        myTextView = findViewById(R.id.textView)
    }

    fun counter(view: View) {
        var currentNumber = textView.text.toString().toInt()
        currentNumber++
        myTextView!!.text = currentNumber.toString()
    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferences!!.contains(MY_COUNTER_KEY)) {
            val counter = sharedPreferences!!.getInt(MY_COUNTER_KEY, 0)
            myTextView!!.text = counter.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        val counter = myTextView!!.text.toString().toInt()
        sharedPreferences!!.edit().putInt(MY_COUNTER_KEY, counter).apply()
    }

}
