package com.andriod.echoofchoices.screen.charater

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.data.Charater
import com.andriod.echoofchoices.screen.helper.CharaterCustomListView


class CharaterListViewActivity : Activity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_charater)

            val listview = findViewById<ListView>(R.id.listview)

            val charaterList = listOf(
                Charater("A","A","A",R.drawable.profile),
                Charater("B","B","B",R.drawable.profile),
                Charater("C","C","C",R.drawable.profile),
                Charater("D","D","D",R.drawable.profile),
            )

        val adapter = CharaterCustomListView(this,charaterList)
        listview.adapter = adapter
     }
}