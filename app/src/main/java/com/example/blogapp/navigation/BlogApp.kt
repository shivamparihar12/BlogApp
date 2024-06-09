package com.example.blogapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blogapp.ui.blogdetail.BlogDetailScreen
import com.example.blogapp.ui.bloglist.BlogListScreen

@Composable
fun BlogApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "blogList") {
        composable("blogList") { BlogListScreen(navController) }
        composable("blogDetail/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toInt()
            Log.d("BlogApp", "Received Post Id: $postId")
            postId?.let { BlogDetailScreen(it) }
        }
    }
}
