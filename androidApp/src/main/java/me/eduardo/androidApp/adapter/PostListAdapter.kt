package me.eduardo.androidApp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.eduardo.androidApp.R
import me.eduardo.androidApp.databinding.ListItemBinding
import me.eduardo.shared.Entity.PostEntity


class PostListAdapter(
    val posts: List<PostEntity>,
    val setFavorite: (PostEntity) -> Unit
) : RecyclerView.Adapter<PostListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return PostListHolder(view)
    }

    override fun onBindViewHolder(holder: PostListHolder, position: Int) {

        holder.bind(posts[position], setFavorite)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}


class PostListHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val binding = ListItemBinding.bind(item)
    fun bind(
        post: PostEntity,
        setFavorite: (PostEntity) -> Unit
    ) {
        binding.tvPostId.text = post.id.toString()
        binding.tvPostTitle.text = post.title


            if(post.favorited){
                binding.ivFavorite.setColorFilter(Color.GREEN)
            }else{
                binding.ivFavorite.setColorFilter(Color.GRAY)
            }


        binding.ivFavorite.setOnClickListener { setFavorite(post) }
    }

}