@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.blogapp.ui.bloglist

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.blogapp.data.model.BlogPost
import com.example.blogapp.viewmodel.BlogViewModel

val TAG="BlogListScreen"


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel = viewModel()) {
    val posts by remember { mutableStateOf(viewModel.posts) }
    Log.d(TAG,"BlogListScreen here")

    LaunchedEffect(Unit) {
        Log.d(TAG, "Posts size on launch: ${posts.size}")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Blog Reader") })
        }
    ) {
        LazyColumn(modifier=Modifier.padding(top = 56.dp)) {
            items(posts) { post ->
                BlogItem(post = post) {
                    navController.navigate("blogDetail/${post.id}")
                }
            }
        }
    }
}
