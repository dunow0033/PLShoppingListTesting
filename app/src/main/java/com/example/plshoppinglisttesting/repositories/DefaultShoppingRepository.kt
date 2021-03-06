package com.example.plshoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import com.example.plshoppinglisttesting.data.local.ShoppingDao
import com.example.plshoppinglisttesting.data.local.ShoppingItem
import com.example.plshoppinglisttesting.data.remote.PixabayAPI
import com.example.plshoppinglisttesting.other.Resource
import com.example.plshoppinglisttesting.data.remote.responses.ImageResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let  Resource.success(it)
                } ?: Resource.error("Unknown error", null)
            } else {
                Resource.error("Unknown error", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server", null)
        }
    }
}