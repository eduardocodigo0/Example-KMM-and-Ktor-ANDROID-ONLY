package me.eduardo.androidApp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        _vm = ViewModelProvider.NewInstanceFactory().create(PostListFragmentViewModel::class.java)
        viewLifecycleOwner.lifecycle.addObserver(_vm)

        _vm.postList.observe(viewLifecycleOwner, Observer { posts ->
            Log.d("KMM", "OBSERVOU ESSA PORRA")

            binding.rvPosts.apply {
                adapter = PostListAdapter(posts)
            }

        })


        return binding.root
    }


    override fun onDestroyView() {

        binding.rvPosts.adapter = null
        _binding = null

        super.onDestroyView()
    }

}