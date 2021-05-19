package me.eduardo.androidApp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                    {post -> setFavorite(post)})
            }

        })


        return binding.root
    }

    fun setFavorite(post: PostEntity){

        lifecycleScope.launch(Dispatchers.IO) {
            _vm.makeFavorite(post)
            _vm.getPosts()
        }


    }

    override fun onDestroyView() {

        binding.rvPosts.adapter = null
        _binding = null

        super.onDestroyView()
    }

}