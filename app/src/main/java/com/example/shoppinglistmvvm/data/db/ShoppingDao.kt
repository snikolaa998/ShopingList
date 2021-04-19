package com.example.shoppinglistmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglistmvvm.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {
    //Ukoliko imamo item sa tim id-ijem, uneta vrednost ce samo da zameni postojecu.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}