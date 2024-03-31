package com.example.lifetrack

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.lifetrack.databinding.ActivityMainBinding


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    val REQUEST_SELECT_IMAGE_IN_ALBUM = 2
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = getSharedPreferences("profile-file", Context.MODE_PRIVATE)
        val nameFromFile = pref.getString("name", "")

        if (!nameFromFile.isNullOrEmpty()) {
            startActivity(Intent(this@MainActivity, TasksActivity::class.java))
            finish()
        }

        binding.save.setOnClickListener {
            val name = binding.nameEd.text.toString()
            val surname = binding.surnameEd.text.toString()
            pref.edit().putString("name", name).apply()
            pref.edit().putString("surname", surname).apply()
            startActivity(Intent(this, TasksActivity::class.java))
        }

        binding.imageView.setOnClickListener {
           selectImageInAlbum()
        }
    }

    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == Activity.RESULT_OK && data != null) {
            val pref = getSharedPreferences("profile-file", Context.MODE_PRIVATE)
            val selectImageUri = data.data
            selectImageUri?.let { uri ->
            val imagePath = uri.toString()
          binding.imageView.load(imagePath)
                pref.edit().putString("imagePath", imagePath).apply()
            }
        }
    }
}


