package db

import kotlin.Long
import kotlin.String

data class Favorites(
  val id: Long
) {
  override fun toString(): String = """
  |Favorites [
  |  id: $id
  |]
  """.trimMargin()
}
