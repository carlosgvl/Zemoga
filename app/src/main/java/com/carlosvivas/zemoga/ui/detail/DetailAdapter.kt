package com.carlosvivas.zemoga.ui.detail

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.carlosvivas.zemoga.R
import com.carlosvivas.zemoga.models.Comments

class DetailAdapter(private val context: Context, private val list: MutableList<Comments>,
                    fragment: Fragment
): RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        var post = list[position]
        holder!!.title!!.setText(post.body)
        holder!!.image!!.visibility= View.GONE

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return DetailAdapter.DetailViewHolder(itemView)
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class DetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_title)
        var image = itemView.findViewById<ImageView>(R.id.iv_read)
    }

}