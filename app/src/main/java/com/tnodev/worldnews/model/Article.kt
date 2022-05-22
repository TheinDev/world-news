package com.tnodev.worldnews.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(  tableName = "articles")
data class Article(


    var id: Int = 1,
    val author: String?,
    val content: String?,
    val description: String?,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("publishedAt")
    @NonNull
    val publishedAt: String = System.currentTimeMillis().toString(),
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?

)
data class Source(
    val id: Any,
    val name: String
)