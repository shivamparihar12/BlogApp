package com.example.blogapp.ui.components

import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.example.blogapp.ui.theme.Purple40

@Composable
fun WebViewComponent(data: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple40)
    ) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
//                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                loadDataWithBaseURL(null, data, "text/html", "UTF-8", null)
            }
        }, update = {
            it.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null)
        })
    }
}

