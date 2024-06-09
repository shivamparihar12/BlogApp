package com.example.blogapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BlogPostEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class BlogDatabase:RoomDatabase() {
    abstract fun blogPostDao():BlogPostDao
}