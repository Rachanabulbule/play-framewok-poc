package models

import play.api.data._
import play.api.data.Forms._
import scala.collection.mutable._

case class Books(id: Int, bookName: String)

object Books {
  val seq = ListBuffer(Books(1, "java"))
  val bookform = Form(
    mapping(
      "id" -> number(min = 1, max = 10),
      "bookName" -> nonEmptyText
    )(Books.apply)(Books.unapply))

  def addBooks(book: Books) = {
    seq.addOne(book)

  }


}

