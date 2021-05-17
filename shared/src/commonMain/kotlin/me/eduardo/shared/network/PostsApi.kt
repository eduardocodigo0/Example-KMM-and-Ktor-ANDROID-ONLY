package me.eduardo.shared.network

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.json
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import me.eduardo.shared.Entity.PostEntity

class PostsApi {

    private val httpClient = HttpClient {
        install(JsonFeature){
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getPosts(): List<PostEntity>{
        return httpClient.get(POSTS_END_POINT)
    }

    companion object{

        private val POSTS_END_POINT = "https://jsonplaceholder.typicode.com/posts"

    }

}