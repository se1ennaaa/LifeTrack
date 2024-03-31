package com.example.lifetrack

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.lifetrack.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProfileData()

    }

    private fun getProfileData() {
        val prefs = getSharedPreferences("profile-file", Context.MODE_PRIVATE)
        val name = prefs.getString("name", "Empty")
        val surname = prefs.getString("surname", "Empty")
        val avatar = prefs.getString("imagePath", "Empty")

        binding.iconBack.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, TasksActivity::class.java))
        }
        binding.nameTv.text = name
        binding.surnameTv.text = surname
        var uri = Uri.parse(avatar)
        binding.image.load(uri)

        // pref.edit().putString("name", name).apply()
        //            pref.edit().putString("surname", surname).apply()
        //            startActivity(Intent(this, TasksActivity::class.java))
        //        }
    }


}

