package me.eduardo.shared

import me.eduardo.shared.Entity.PostEntity
import me.eduardo.shared.db.Database
import me.eduardo.shared.db.DatabaseDriverFactory

class DataSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)


    suspend fun isPostFavorite(id: Long): Boolean{
        return try {
            database.isFavorite(id)
        }catch (err: Exception){
            false
        }
    }

    suspend fun insertFavorite(post: PostEntity): Boolean{
        return try {
            database.setFavorite(post)
            true
        }catch (err: Exception){
            false
        }

    }
    suspend fun removeFavorite(post: PostEntity): Boolean{
        return try {
            database.removeFavorite(post)
            true
        }catch (err: Exception){
            false
        }
    }

}