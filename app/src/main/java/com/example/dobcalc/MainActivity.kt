package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelecionaDat : TextView? = null//private makes only usable for this class Main Activity
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelecionaDat = findViewById(R.id.tvSelectDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        buttonDatePicker.setOnClickListener{
//            Toast.makeText(this,
//                "buttonDatePicker pressed", Toast.LENGTH_LONG).show()
            clickDatePicker()
        }

    }
    //EXECUTION
    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()//object
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "day of month was $selectedDayOfMonth, month was ${selectedMonth+1}" + "Year was $selectedYear" ,
                    Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelecionaDat?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time / 60000//time = get.time() 60000 minutes time past 1970mileseconds

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))// current time

                val currentDateInMinutes = currentDate.time/60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()

            },
            year,
            month,
            day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}