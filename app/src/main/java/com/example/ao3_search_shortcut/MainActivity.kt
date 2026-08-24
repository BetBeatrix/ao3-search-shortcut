package com.example.ao3_search_shortcut

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val processText = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)?.toString()
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)

        val selectedText = processText ?: sharedText

        if (selectedText != null) {
            val workSearch = selectedText.trim()
            val workId = workSearch.toLongOrNull()
            val customUrl = if (workId == null){
                val encodedSearch = Uri.encode(workSearch)
                "https://archiveofourown.org/works/search?commit=Search&work_search%5Bquery%5D=$encodedSearch"
            } else {
                "https://archiveofourown.org/works/$workId"
            }




            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(customUrl))
            startActivity(browserIntent)
        }

        finish()
    }
}

