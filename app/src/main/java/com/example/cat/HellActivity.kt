package com.example.cat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cat.databinding.ActivityHellBinding

class HellActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHellBinding
    private lateinit var adapter: CatAdapter
    lateinit var catList : ArrayList<String?>
    var TAG = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHellBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerViewHell.adapter = adapter
        binding.recyclerViewHell.layoutManager =
        GridLayoutManager(this@HellActivity, 2)

    }
}