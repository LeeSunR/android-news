package kr.leesunr.news.webview

import android.os.Bundle
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
        binding.webView.loadUrl(url!!)
    }
}