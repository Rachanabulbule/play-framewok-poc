package controllers

import java.lang.ProcessBuilder.Redirect

import javax.inject._
import models._
import play.api.mvc._

class BookController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  def displayAllBooks() = Action {
    Ok(views.html.displaybook(BooksStore.list))
  }

  def insertBook() = Action { implicit request =>
    Ok(views.html.insertbookform(BooksStore.insertbookform))
  }

  def save() = Action { implicit request =>
    val bookData: Book = BooksStore.insertbookform.bindFromRequest.get
    BooksStore.addBooks(bookData)
    Redirect(routes.BookController.displayAllBooks)
  }

  def getBookById(id: Int) = Action { implicit request =>
    val list = BooksStore.getBookById(id)
    if (list.isEmpty) Ok(s"Book not found of Id:$id")
    else Ok(views.html.displaybook(list))
  }

  def getBookByTitle(bookName: String) = Action {
    val list = BooksStore.getBookByTitle(bookName)
    if (list.isEmpty) Ok(s"Book not found of Title:$bookName")
    else Ok(views.html.displaybook(list))
  }

  def getBookByAuthor(author: String) = Action {
    val list = BooksStore.getBookByAuthor(author)
    if (list.isEmpty) Ok(s"Book not found of Author:$author")
    else Ok(views.html.displaybook(list))
  }
}
