package com.example.coffeshopapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffeshopapp.Helper.ManagmentCart
import com.example.coffeshopapp.R
import com.example.coffeshopapp.databinding.ActivityDetailBinding
import com.example.coffeshopapp.domain.ItemsModel

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            SmallBtn.setOnClickListener {
                SmallBtn.setBackgroundResource(R.drawable.brown_full_corner_bg)
                MediumBtn.setBackgroundResource(0)
                LargeBtn.setBackgroundResource(0)
            }

            MediumBtn.setOnClickListener {
                SmallBtn.setBackgroundResource(0)
                MediumBtn.setBackgroundResource(R.drawable.brown_full_corner_bg)
                LargeBtn.setBackgroundResource(0)
            }

            LargeBtn.setOnClickListener {
                SmallBtn.setBackgroundResource(0)
                MediumBtn.setBackgroundResource(0)
                LargeBtn.setBackgroundResource(R.drawable.brown_full_corner_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item=intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$"+item.price
            ratingTxt.text = item.rating.toString()

            addToCartBtn.setOnClickListener {
                item.numberInCart= Integer.valueOf(
                    numberInCartTxt.text.toString()
                )
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener { finish() }
            plusBtn.setOnClickListener {
                numberInCartTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusBtn.setOnClickListener {
                if(item.numberInCart>0){
                    numberInCartTxt.text=(item.numberInCart-1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}