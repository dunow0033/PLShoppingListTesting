package com.example.plshoppinglisttesting.repositories

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import com.example.plshoppinglisttesting.data.local.ShoppingItem
import retrofit2.Response
import com.example.plshoppinglisttesting.other.Resource
import com.example.plshoppinglisttesting.data.remote.responses.ImageResponse

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}