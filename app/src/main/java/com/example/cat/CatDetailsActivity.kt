package com.example.cat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cat.databinding.ActivityCatDetailsBinding
import com.example.cat.databinding.ActivityHellBinding
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
    public var hellList : MutableList<String> = mutableListOf()
    val sharedPref = this@CatDetailsActivity?.getPreferences(Context.MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCatDetailsMeow.setOnClickListener {
            randomCat()
        }

        binding.buttonCatDetailAddHell.setOnClickListener {
            Log.d(TAG, "HELL : $hellList")
            randomCat()
        }

        /*binding.imageButtonCatDetailsReturn.setOnClickListener{
            val toHellIntent = Intent(this, TitleScreenActivity::class.java)
            val stringList = ArrayList(hellList)
            toHellIntent.putStringArrayListExtra("hell", stringList)
            startActivity(toHellIntent)
            finish()
        }
   */ }

    private fun randomCat(){
        val catApi = RetrofitHelper.getInstance().create(CatImg :: class.java)
        val catCall = catApi.getCatImg()

        catCall.enqueue(object : Callback<Cat> {
            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                Log.d(TAG, "onResponse : ${response.body()}")
                val cat = response.body()
                if (cat != null) {
                    with (sharedPref.edit()) {
                        putString(cat.url)
                        apply()
                    }
                    hellList.add(cat.url)
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