package com.davidups.starwars.core.platform

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Insert
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}