package com.gmail.spencerdamon1974.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.*
import org.kodein.di.bindings.ErasedContext.value

@Dao
interface ShoppingDao {

    // The following functions can't be called via the main thread, must be called
    // asynchronically in kotlin using coroutines, use the suspend keyword
    // Upsert update/insert if the id of the item already exists in the db, it will update the
    // item in the db, if it doesn't exist is will insert the item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)


    @Delete
    suspend fun delete(item: ShoppingItem)

    // Pass the shoppingItem into the LiveData object making it efficient to update RecyclerView
    @Query( "SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}