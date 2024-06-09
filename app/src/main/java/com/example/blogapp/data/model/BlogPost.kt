package com.example.blogapp.data.model

import java.util.Date

data class BlogPost(
    val id: Int,
    val title: Rendered,
    val content: Rendered,
    val date:String
)

data class Rendered(
    val rendered: String
)
