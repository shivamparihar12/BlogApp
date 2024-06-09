@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.blogapp.ui.blogdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.blogapp.ui.components.WebViewComponent
import com.example.blogapp.viewmodel.BlogViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blogapp.ui.theme.BrightColors

@Composable
fun BlogDetailScreen(postId: Int, viewModel: BlogViewModel = viewModel()) {
    val post = viewModel.posts.find { it.id == postId }
    post?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = it.title.rendered,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.background(BrightColors.secondary).padding(10.dp),
            )
            WebViewComponent(data = it.content.rendered)
        }
    } ?: Text(
        text = "Post not found",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

