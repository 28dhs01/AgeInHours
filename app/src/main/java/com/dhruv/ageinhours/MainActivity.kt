package com.dhruv.ageinhours

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var displayDate: TextView? = null
    var displayHours: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn : Button = findViewById(R.id.btnSelectDate)
         displayDate  = findViewById(R.id.tvDisplayDate)
        displayHours = findViewById(R.id.tvDisplayHours)
        btn.setOnClickListener {
             chooseDate()
        }

    }
    private fun chooseDate(){
        val calender = Calendar.getInstance()
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "${selectedDay}/${selectedMonth+1}/${selectedYear}"
            displayDate?.text = selectedDate
            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val hoursPassedTillThatDate = theDate.time/3600000
            val curDate = sdf.parse(
                sdf.format(System.currentTimeMillis())
            )
            val hoursPassedTillCurDate = curDate.time/3600000
            val hoursLived = hoursPassedTillCurDate-hoursPassedTillThatDate
            displayHours?.text = hoursLived.toString()


        },year,month,day)
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}