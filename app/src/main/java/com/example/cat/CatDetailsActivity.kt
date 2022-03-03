package com.example.cat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cat.databinding.ActivityCatDetailsBinding
import com.example.cat.databinding.ActivityTitleScreenBinding
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class CatDetailsActivity : AppCompatActivity() {
    companion object {
        val TAG = "cat details"
    }

    private lateinit var binding: ActivityCatDetailsBinding
    private lateinit var adapter: CatAdapter
    var hellList : MutableList<String> = mutableListOf()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.groupCatDetailsList.visibility = View.GONE

        binding.buttonCatDetailsMeow.setOnClickListener {
            randomCat()
        }


        binding.buttonCatDetailsHell.setOnClickListener {
            binding.groupCatDetails.visibility = View.GONE
            binding.groupCatDetailsList.visibility = View.VISIBLE
            adapter = CatAdapter(hellList)
            binding.recyclerViewCatDetails.adapter = adapter
            binding.recyclerViewCatDetails.layoutManager =
                GridLayoutManager(this@CatDetailsActivity, 2)
        }

        binding.imageButtonCatDetailsReturn.setOnClickListener{
            binding.groupCatDetails.visibility = View.VISIBLE
            binding.groupCatDetailsList.visibility = View.GONE
        }

    }

    private fun randomCat(){
        val catApi = RetrofitHelper.getInstance().create(CatImg :: class.java)
        val catCall = catApi.getCatImg()

        catCall.enqueue(object : Callback<Cat> {
            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                Log.d(TAG, "onResponse : ${response.body()}")
                val cat = response.body()
                binding.buttonCatDetailAddHell.setOnClickListener {
                    if (cat != null) {
                        if(!hellList.contains(cat.url)){
                            hellList.add(cat.url)
                        }
                    randomCat()
                    }
                }
                Picasso.get().load(cat?.url).into(binding.imageViewCatDetailsCat)
                binding.textViewCatDetailsArtist.text = cat?.artist

            }

            override fun onFailure(call: Call<Cat>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}