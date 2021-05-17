package me.eduardo.androidApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.eduardo.androidApp.R
import me.eduardo.androidApp.databinding.ListItemBinding
import me.eduardo.shared.Entity.PostEntity

class PostListAdapter(val posts: List<PostEntity>): RecyclerView.Adapter<PostListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return PostListHolder(view)
    }

    override fun onBindViewHolder(holder: PostListHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}


class PostListHolder(item: View): RecyclerView.ViewHolder(item) {

    private val binding = ListItemBinding.bind(item)

    fun bind(post: PostEntity){
        binding.tvPostId.text = post.id.toString()
        binding.tvPostTitle.text = post.title
    }

}