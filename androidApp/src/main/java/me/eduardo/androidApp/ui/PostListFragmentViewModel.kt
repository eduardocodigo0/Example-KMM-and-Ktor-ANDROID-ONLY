package me.eduardo.androidApp.ui

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.eduardo.shared.Entity.PostEntity
import me.eduardo.shared.network.PostsApi

class PostListFragmentViewModel: ViewModel(), LifecycleObserver {

    private val api = PostsApi()
    private val _postList: MutableLiveData<List<PostEntity>> = MutableLiveData()
    val postList: LiveData<List<PostEntity>> get() = _postList



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getPosts(){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = api.getPosts()
                _postList.postValue(data)

            }catch (err: Exception){
                Log.e("KMM", "ERRO -> ${err.message}")
            }

        }
    }

}