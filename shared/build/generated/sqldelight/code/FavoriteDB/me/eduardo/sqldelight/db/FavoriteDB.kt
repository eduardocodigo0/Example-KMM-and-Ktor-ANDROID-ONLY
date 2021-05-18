package me.eduardo.sqldelight.db

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import db.FavoriteDBQueries
import me.eduardo.sqldelight.db.shared.newInstance
import me.eduardo.sqldelight.db.shared.schema

interface FavoriteDB : Transacter {
  val favoriteDBQueries: FavoriteDBQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = FavoriteDB::class.schema

    operator fun invoke(driver: SqlDriver): FavoriteDB = FavoriteDB::class.newInstance(driver)}
}
