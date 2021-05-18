package me.eduardo.sqldelight.db.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import db.FavoriteDBQueries
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.jvm.JvmField
import kotlin.reflect.KClass
import me.eduardo.sqldelight.db.FavoriteDB

internal val KClass<FavoriteDB>.schema: SqlDriver.Schema
  get() = FavoriteDBImpl.Schema

internal fun KClass<FavoriteDB>.newInstance(driver: SqlDriver): FavoriteDB = FavoriteDBImpl(driver)

private class FavoriteDBImpl(
  driver: SqlDriver
) : TransacterImpl(driver), FavoriteDB {
  override val favoriteDBQueries: FavoriteDBQueriesImpl = FavoriteDBQueriesImpl(this, driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE favorites (
          |    id INTEGER NOT NULL PRIMARY KEY
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

private class FavoriteDBQueriesImpl(
  private val database: FavoriteDBImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), FavoriteDBQueries {
  internal val isFavorite: MutableList<Query<*>> = copyOnWriteList()

  override fun isFavorite(id: Long): Query<Long> = IsFavoriteQuery(id) { cursor ->
    cursor.getLong(0)!!
  }

  override fun insertFavorite(id: Long?) {
    driver.execute(-1356770343, """INSERT INTO favorites (id) VALUES (?)""", 1) {
      bindLong(1, id)
    }
    notifyQueries(-1356770343, {database.favoriteDBQueries.isFavorite})
  }

  override fun deleteFavorite(id: Long) {
    driver.execute(-437910325, """DELETE FROM favorites WHERE id = ?""", 1) {
      bindLong(1, id)
    }
    notifyQueries(-437910325, {database.favoriteDBQueries.isFavorite})
  }

  private inner class IsFavoriteQuery<out T : Any>(
    @JvmField
    val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(isFavorite, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(-274108598,
        """SELECT * FROM favorites WHERE id = ?""", 1) {
      bindLong(1, id)
    }

    override fun toString(): String = "FavoriteDB.sq:isFavorite"
  }
}
