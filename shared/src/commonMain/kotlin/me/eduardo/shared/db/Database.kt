package me.eduardo.shared.db

import me.eduardo.shared.Entity.PostEntity
import me.eduardo.sqldelight.db.FavoriteDB

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = FavoriteDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.favoriteDBQueries

    internal fun setFavorite(post: PostEntity){

        dbQuery.insertFavorite(
            id = post.id.toLong()
        )

    }

    internal fun removeFavorite(post: PostEntity){

        dbQuery.deleteFavorite(
            id = post.id.toLong()
        )

    }

    internal fun isFavorite(id: Long): Boolean{

        val result = dbQuery.isFavorite(id).executeAsOneOrNull()

        return result != null

    }

    internal fun getAll(): List<Long>{
        return dbQuery.getAll().executeAsList()
    }


}
