package models

import play.api.data._
import play.api.data.Forms._
import scala.collection.mutable._

case class Book(id: Int, bookName: String, author: String)

object BooksStore {
  val list = ListBuffer(Book(1, "java", "durga"))
  val insertbookform = Form(
    mapping(
      "id" -> number(min = 1, max = 10),
      "bookName" -> nonEmptyText,
      "author" -> nonEmptyText
    )(Book.apply)(Book.unapply))

  def addBooks(book: Book) = {
    list.addOne(book)

  }

  def getBookById(id: Int) = {
    list.filter(_.id == id)
  }

  def getBookByTitle(bookName: String) = {
    list.filter(_.bookName == bookName)
  }

  def getBookByAuthor(author: String) = {
    list.filter(_.author == author)
  }
}
