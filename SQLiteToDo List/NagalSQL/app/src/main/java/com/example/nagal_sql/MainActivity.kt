package com.example.nagal_sql

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var save: Button
    private lateinit var refresh: Button
    private lateinit var ToDo: EditText
    private lateinit var Time: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        val helper = DatabaseHelper(this)
        val arrayList: ArrayList<String> = helper.getAllList() as ArrayList<String>
        ToDo = findViewById(R.id.ToDo)
        Time = findViewById(R.id.Time)
        save = findViewById(R.id.save)
        refresh = findViewById(R.id.refresh)
        val listView: ListView = findViewById(R.id.listView)
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(this@MainActivity,
            android.R.layout.simple_list_item_1, arrayList as List<Any?>)
        listView.adapter = arrayAdapter
        save.setOnClickListener {
            arrayList.clear()
            arrayList.addAll(helper.getAllList())
            arrayAdapter.notifyDataSetChanged()
            listView.invalidateViews()
            listView.refreshDrawableState()
        }
        refresh.setOnClickListener {
            if (ToDo.text.toString().isNotEmpty() && Time.text.toString().isNotEmpty()) {
                if (helper.addData(ToDo.text.toString(), Time.text.toString())) {
                    Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "NOT Inserted", Toast.LENGTH_LONG).show()
                }
            } else {
                ToDo.error = "Enter ToDo"
                Time.error = "Enter Time"
            }
        }
    }
}