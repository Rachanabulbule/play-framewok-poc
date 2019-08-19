package controllers

import java.lang.ProcessBuilder.Redirect

import javax.inject._
import models._

import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {


  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  import play.api.http.HttpEntity

  def index(name: String) = Action {
    Ok("done")
  }

  def echo(name: String) = Action { implicit request =>
    Ok("Got request [" + request + "]")
  }

  def create() = Action { implicit request =>
    Ok(views.html.bookform(Books.bookform))
  }

  def save() = Action { implicit request =>
    val bookData: Books = Books.bookform.bindFromRequest.get
    Books.addBooks(bookData)
    Redirect(routes.HomeController.displayAllBooks)
  }

  def displayAllBooks() = Action {
    Ok(views.html.display(Books.seq))
  }

}
