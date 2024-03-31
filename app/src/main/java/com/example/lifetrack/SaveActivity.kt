package com.example.lifetrack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.example.lifetrack.databinding.ActivitySaveBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class SaveActivity : AppCompatActivity() {
    lateinit var binding: ActivitySaveBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolBar2.profileIcon.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnSave.setOnClickListener {
            val list = getArrayList("list")
            val text = binding.editTx.text.toString()
            list.add(text)
            saveArrayList(list, "list")
            startActivity(Intent(this, TasksActivity::class.java))
            finish()
        }
    }

    fun saveArrayList(list: ArrayList<String>, key: String?) {
        val prefs = getSharedPreferences("file-task", MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(list)
        prefs.edit().putString(key, json).apply()
    }

    fun getArrayList(key: String?): ArrayList<String> {
        val prefs = getSharedPreferences("file-task", MODE_PRIVATE)
        val gson = Gson()
        val json: String? = prefs.getString(key, "")
        if (json?.isNotEmpty() == true) {
            val type: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
            return gson.fromJson(json, type)
        } else {
            return arrayListOf()
        }
    }
}






