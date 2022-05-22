package com.tnodev.worldnews.repository

import androidx.room.TypeConverter
import com.tnodev.worldnews.model.Source

class TypeConvert {

    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}