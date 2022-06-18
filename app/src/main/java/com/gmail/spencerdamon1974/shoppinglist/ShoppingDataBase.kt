package com.gmail.spencerdamon1974.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    // specify which entity belongs to the db, create an array b/c could be many
    // entities. Pass in the class of our shopping item
    entities = [ShoppingItem::class],
    // everytime we update the db, we need to update the version or Room will throw an error
    version = 1
        )

abstract class ShoppingDataBase : RoomDatabase() {

    //create a fun that refers to our DAO shopping object
    abstract fun getShoppingDao(): ShoppingDao

    // similar to the static keyword in java, create a singleton object b/c we only
    // want one instance of the same db at the same time.
    companion object {
        // annotate with volatile as it writes instantly to other threads, we want to
        // ensure that it only writes to one instance at a time otherwise if two threads
        // would want to write to two instances of the shopping db at a time.
        @Volatile
        private var instance: ShoppingDataBase? = null

        //write a method to instantiate our db
        private fun createDatabase(context: Context) {
            Room.databaseBuilder(context.applicationContext,
            ShoppingDataBase::class.java, "ShoppingDB.db").build()
        }
    }

}