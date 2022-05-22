package com.tnodev.worldnews.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tnodev.worldnews.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: Article): Long

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllNews(): Flow<List<Article>>
}