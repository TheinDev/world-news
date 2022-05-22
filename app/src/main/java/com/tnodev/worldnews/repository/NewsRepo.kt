package com.tnodev.worldnews.repository

import com.tnodev.worldnews.api_service.ApiService
import com.tnodev.worldnews.api_service.RetrofitService
import com.tnodev.worldnews.model.Article

class NewsRepo( val db: NewsDatabase) {

    suspend fun getBreakingNews( category : String, pageNumber: Int) =
        RetrofitService.api.getBreakingNews(category, pageNumber)



    suspend fun insertNews(article: Article) = db.getDao().insertNews(article)

    suspend fun deleteNews(article: Article) = db.getDao().deleteNews(article)

    fun getSavedNews() = db.getDao().getAllNews()
}