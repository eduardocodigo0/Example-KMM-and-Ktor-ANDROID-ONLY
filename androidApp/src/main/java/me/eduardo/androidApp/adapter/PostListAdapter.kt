package me.eduardo.androidApp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import me.eduardo.androidApp.R
import me.eduardo.androidApp.databinding.ListItemBinding
import me.eduardo.shared.Entity.PostEntity


class PostListAdapter(
    val posts: List<PostEntity>,
    val changeColor: (Long, ImageView) -> Unit,
    val setFavorite: (PostEntity, ImageView) -> Unit
) : RecyclerView.Adapter<PostListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return PostListHolder(view)
    }

    override fun onBindViewHolder(holder: PostListHolder, position: Int) {

        holder.bind(posts[position], changeColor, setFavorite)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}


class PostListHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val binding = ListItemBinding.bind(item)

    fun bind(
        post: PostEntity,
        changeColor: (Long, ImageView) -> Unit,
        setFavorite: (PostEntity, ImageView) -> Unit
    ) {
        binding.tvPostId.text = post.id.toString()
        binding.tvPostTitle.text = post.title

        changeColor(post.id.toLong(), binding.ivFavorite)

        binding.ivFavorite.setOnClickListener { setFavorite(post, binding.ivFavorite) }
    }

}