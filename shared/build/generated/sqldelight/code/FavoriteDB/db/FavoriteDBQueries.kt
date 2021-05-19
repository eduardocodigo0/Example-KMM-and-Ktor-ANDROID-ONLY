package db

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Long

interface FavoriteDBQueries : Transacter {
  fun isFavorite(id: Long): Query<Long>

  fun getAll(): Query<Long>

  fun insertFavorite(id: Long?)

  fun deleteFavorite(id: Long)
}
