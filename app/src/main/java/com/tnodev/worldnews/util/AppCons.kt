package com.tnodev.worldnews.util

import com.tnodev.worldnews.model.Article

class AppCons {
    companion object {
        const val API_KEY = "e454fc7e91c74e17ab6b9d8aa6639036"
        const val BASE_URL = "https://newsapi.org"

        const val PAGE_LIMIT = 20
        var detailArticle: Article? = null
    }
}

