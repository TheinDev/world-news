package com.tnodev.worldnews.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tnodev.worldnews.model.Article

interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: Article): Long

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllNews(): LiveData<List<Article>>
}