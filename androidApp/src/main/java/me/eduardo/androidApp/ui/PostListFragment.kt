package me.eduardo.androidApp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.eduardo.androidApp.R
import me.eduardo.androidApp.adapter.PostListAdapter
import me.eduardo.androidApp.databinding.FragmentPostListBinding
import me.eduardo.shared.Entity.PostEntity


class PostListFragment : Fragment() {

    private lateinit var _vm: PostListFragmentViewModel
    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        _vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            .create(PostListFragmentViewModel::class.java)
        viewLifecycleOwner.lifecycle.addObserver(_vm)

        _vm.postList.observe(viewLifecycleOwner, Observer { posts ->
            binding.rvPosts.apply {
                adapter = PostListAdapter(posts,
                    {id, star -> selectColor(id, star)},
                    {post, star -> setFavorite(post, star)})
            }

        })


        return binding.root
    }
    fun selectColor(id: Long,star: ImageView){
        lifecycleScope.launch(Dispatchers.IO) {
            if (_vm.isItFavorited(id)) {
                star.setColorFilter(Color.GREEN)
            } else {
                star.setColorFilter(Color.GRAY)
            }
        }
    }

    fun setFavorite(post: PostEntity, star: ImageView){

        lifecycleScope.launch(Dispatchers.IO) {
            _vm.makeFavorite(post)

            if(_vm.isItFavorited(post.id.toLong())){
                star.setColorFilter(Color.GREEN)
            }else{
                star.setColorFilter(Color.GRAY)
            }
        }


    }

    override fun onDestroyView() {

        binding.rvPosts.adapter = null
        _binding = null

        super.onDestroyView()
    }

}