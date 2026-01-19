package com.example.coffeshopapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshopapp.Repository.MainRepository
import com.example.coffeshopapp.domain.BannerModel
import com.example.coffeshopapp.domain.CategoryModel
import com.example.coffeshopapp.domain.ItemsModel

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>>{
        return repository.loadItemCategory(categoryId)
    }
}