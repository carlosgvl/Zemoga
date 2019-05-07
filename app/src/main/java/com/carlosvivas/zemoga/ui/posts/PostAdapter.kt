package com.carlosvivas.zemoga.ui.posts

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.carlosvivas.zemoga.R
import com.carlosvivas.zemoga.models.Post

class PostAdapter(private val context: Context, private val list: MutableList<Post>,
                  fragment: Fragment): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val listener: PostAdapter.onItemClickListener

    init {
        this.listener = fragment as PostAdapter.onItemClickListener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var post = list[position]
        holder!!.title!!.setText(post.title)

        if(post.favorite){
            holder.favorite.visibility= View.VISIBLE
        }

        if(post.id > 19){
            holder.image.visibility= View.GONE
        }

        holder.layout!!.setOnClickListener {
            listener.itemDetail(post.id, post.userId, post.body)
            holder.image.visibility= View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return PostAdapter.PostViewHolder(itemView)
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        var image = itemView.findViewById<ImageView>(R.id.iv_read)
        var favorite = itemView.findViewById<ImageView>(R.id.iv_start)


    }

    interface onItemClickListener {
        fun itemRemoveClick(post: Post)
        fun itemDetail(postId : Int, postUserId: Int, postBody:String)
    }
}