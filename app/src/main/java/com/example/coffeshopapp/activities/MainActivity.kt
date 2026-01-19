package com.example.coffeshopapp.activities

import android.R.attr.category
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeshopapp.R
import com.example.coffeshopapp.adapters.CategoryAdapter
import com.example.coffeshopapp.adapters.PopularAdapter
import com.example.coffeshopapp.databinding.ActivityMainBinding
import com.example.coffeshopapp.databinding.ActivitySplashBinding
import com.example.coffeshopapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel= MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initCategory()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initPopular() {
        binding.apply {
            progressBarPopular.visibility=View.VISIBLE
            viewModel.loadPopular().observeForever {
                popularView.layoutManager= LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,false)

                popularView.adapter= PopularAdapter(it)
                progressBarPopular.visibility= View.GONE
            }
            viewModel.loadPopular()
        }
    }

    private fun initCategory() {
        binding.apply {
            progressBarCategory.visibility= View.VISIBLE
            viewModel.loadCategory().observeForever {
                categoryView.adapter = CategoryAdapter(it)
                categoryView.layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.HORIZONTAL, false)
                progressBarCategory.visibility = View.GONE
            }
            viewModel.loadCategory()
        }
    }

    private fun initBanner() {
        binding.apply {
            progressBarBanner.visibility= View.VISIBLE
            viewModel.loadBanner().observeForever {
                Glide.with(this@MainActivity)
                    .load(it[0].url)
                    .into(banner)
                progressBarBanner.visibility= View.GONE
            }
            viewModel.loadBanner()
        }
    }
}