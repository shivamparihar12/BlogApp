package com.example.blogapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.blogapp.data.local.BlogDatabase
import com.example.blogapp.data.local.BlogPostEntity
import com.example.blogapp.data.model.BlogPost
import com.example.blogapp.data.model.Rendered
import com.example.blogapp.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class BlogViewModel(application: Application) : AndroidViewModel(application) {
    private val database = Room.databaseBuilder(
        application,
        BlogDatabase::class.java,
        "blog_database"
    ).build()

    private val blogPostDao = database.blogPostDao()

    private val _posts = mutableStateListOf<BlogPost>()
    val posts: List<BlogPost> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPosts(10, 1)
                Log.d("BlogViewModel", "Fetched posts from API: ${response.size}")
                val entities = response.map { post ->
                    BlogPostEntity(
                        id = post.id,
                        title = post.title.rendered,
                        content = post.content.rendered,
                        date = post.date // Assuming the API response contains a date field
                    )
                }
                blogPostDao.deleteAll()
                blogPostDao.insertAll(entities)

                val cachedPosts = blogPostDao.getAllPosts().map { entity ->
                    BlogPost(
                        id = entity.id,
                        title = Rendered(entity.title),
                        content = Rendered(entity.content),
                        date = entity.date
                    )
                }
                Log.d("BlogViewModel", "Fetched posts from DB: ${cachedPosts.size}")
                _posts.clear()
                _posts.addAll(cachedPosts)
                Log.d("BlogViewModel", "Posts updated: ${_posts.size}")
            } catch (e: Exception) {
                Log.e("BlogViewModel", "Error fetching posts", e)
                val cachedPosts = blogPostDao.getAllPosts().map { entity ->
                    BlogPost(
                        id = entity.id,
                        title = Rendered(entity.title),
                        content = Rendered(entity.content),
                        date = entity.date
                    )
                }
                _posts.clear()
                _posts.addAll(cachedPosts)
                Log.d("BlogViewModel", "Fetched cached posts: ${_posts.size}")
            }
        }
    }
}
