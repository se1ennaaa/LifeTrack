package com.example.lifetrack
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifetrack.databinding.ActivityTasksBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList


class TasksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTasksBinding
    private val riversArray = arrayListOf("")
    private var adapter = MyAdapter(riversArray)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MyAdapter(getArrayList("list"))
        binding.myRecyclerView.adapter = adapter

        binding.idFABHome.setOnClickListener {
            startActivity(Intent(this@TasksActivity, SaveActivity::class.java))
        }

        binding.toolBar2.profileIcon.setOnClickListener {
            startActivity(Intent(this@TasksActivity, ProfileActivity::class.java))
        }
    }


    private fun getArrayList(key: String?): ArrayList<String> {
        val prefs = getSharedPreferences("file-task", MODE_PRIVATE)
        val gson = Gson()
        val json: String? = prefs.getString(key, "")
        return if (json?.isNotEmpty() == true) {
            val type: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
}



