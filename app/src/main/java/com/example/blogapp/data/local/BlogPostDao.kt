package com.example.blogapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogPostDao {
    @Query("SELECT * FROM blog_posts")
    suspend fun getAllPosts(): List<BlogPostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<BlogPostEntity>)

    @Query("DELETE FROM blog_posts")
    suspend fun deleteAll()
}