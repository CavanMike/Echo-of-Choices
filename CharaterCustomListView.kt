package com.andriod.echoofchoices.screen.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.andriod.echoofchoices.R
import com.andriod.echoofchoices.data.Charater
import java.text.FieldPosition

class CharaterCustomListView(private val context : Context,
    private val charaterList:List<Charater> ) : BaseAdapter(){
    override fun getCount(): Int = charaterList.size

    override fun getItem(position: Int): Any = charaterList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent : ViewGroup?):View{
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.activity_charater,parent, false)

        val profilePic = view.findViewById<ImageView>(R.id.imageview_charater_pic)
        val fullname = view.findViewById<TextView>(R.id.textview_fullname)

        val charater = charaterList[position]

        profilePic.setImageResource(charater.photoRes)
        fullname.setText("${charater.lastname}, ${charater.firstname} , ${charater.middlename}")

        return view
     }
}