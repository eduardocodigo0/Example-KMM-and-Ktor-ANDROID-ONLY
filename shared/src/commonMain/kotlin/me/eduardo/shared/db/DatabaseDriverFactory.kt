package me.eduardo.shared.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

     fun createDriver(): SqlDriver

}