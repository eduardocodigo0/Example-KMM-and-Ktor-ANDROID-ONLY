package me.eduardo.androidApp.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors.getColor
import kotlinx.coroutines.*
import me.eduardo.androidApp.R
import me.eduardo.androidApp.databinding.ListItemBinding
import me.eduardo.shared.Entity.PostEntity
import org.w3c.dom.Entity

class PostListAdapter(val posts: List<PostEntity>, val setFavorite: suspend (PostEntity) -> Boolean, val isFavorite: suspend (Long) -> Boolean): RecyclerView.Adapter<PostListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return PostListHolder(view)
    }

    override fun onBindViewHolder(holder: PostListHolder, position: Int) {

        holder.bind(posts[position], setFavorite, isFavorite)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}


class PostListHolder(item: View): RecyclerView.ViewHolder(item) {

    private val binding = ListItemBinding.bind(item)
    private val job = SupervisorJob()
    private val coroutineContext = job + Dispatchers.IO
    val EduardoScope = CoroutineScope(coroutineContext)

   fun bind(post: PostEntity, setFavorite: suspend (PostEntity) -> Boolean, isFavorite: suspend (Long) -> Boolean ){
        binding.tvPostId.text = post.id.toString()
        binding.tvPostTitle.text = post.title

       EduardoScope.launch{
            if(isFavorite(post.id.toLong())){
                binding.ivFavorite.setColorFilter(Color.GREEN)
            }else{
                binding.ivFavorite.setColorFilter(Color.GRAY)
            }
        }

        binding.ivFavorite.setOnClickListener{
            EduardoScope.launch {
                setFavorite(post)

                if(isFavorite(post.id.toLong())){
                    binding.ivFavorite.setColorFilter(Color.GREEN)
                }else{
                    binding.ivFavorite.setColorFilter(Color.GRAY)
                }
            }
        }
    }

}