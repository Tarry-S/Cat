package com.example.cat

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.cat.databinding.ActivityCatDetailsBinding
import com.example.cat.databinding.ActivityHellBinding
import com.example.cat.databinding.ActivityTitleScreenBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTitleScreenBinding

    val TAG = "TitleScreenActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonMainMeow.setOnClickListener {
            val nyaIntent = Intent(this, CatDetailsActivity::class.java)
            startActivity(nyaIntent)
        }


        binding.buttonMainViewHell.setOnClickListener {
            val hellIntent = Intent(this, HellActivity::class.java)
            startActivity(hellIntent)
        }
    }
}