package kr.leesunr.news.webview

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kr.leesunr.news.webview.databinding.ActivityWebviewBinding


class WebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")

        binding.toolbar.title = title

        binding.webView.webViewClient = MyWebViewClient()

        binding.webView.loadUrl(url!!)
    }

    inner class MyWebViewClient: WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return false
        }
    }
}