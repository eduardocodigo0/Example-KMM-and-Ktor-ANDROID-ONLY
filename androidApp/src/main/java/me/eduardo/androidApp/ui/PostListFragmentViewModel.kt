package me.eduardo.androidApp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.eduardo.shared.DataSDK
import me.eduardo.shared.Entity.PostEntity
import me.eduardo.shared.db.DatabaseDriverFactory
import me.eduardo.shared.network.PostsApi

class PostListFragmentViewModel(application: Application): AndroidViewModel(application), LifecycleObserver {

    private val api = PostsApi()
    private val _postList: MutableLiveData<List<PostEntity>> = MutableLiveData()
    val postList: LiveData<List<PostEntity>> get() = _postList
    val db = DataSDK(DatabaseDriverFactory(application))



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

    suspend fun makeFavorite(post: PostEntity): Boolean{

       return if(isItFavorited(post.id.toLong())){
            db.removeFavorite(post)
        }else{
            db.insertFavorite(post)
        }

    }

    suspend fun isItFavorited(id: Long): Boolean{
            return db.isPostFavorite(id)
    }

}