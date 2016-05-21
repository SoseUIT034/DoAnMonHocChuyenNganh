package controllers

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, Controller}
import service.ProductService
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future
import models.{Product,ProductForm}

/**
 * The user controller.
 *
 * @param messagesApi The Play messages API.
 * @param productService The user service implementation.
 */
 class ProductController @Inject() (productService: ProductService, val messagesApi: MessagesApi) extends Controller with I18nSupport {

/**
 * Home Page.
 *
 * @return The result to display.
 */
  def home = Action.async { implicit  request =>
  productService.listAllProducts map { products =>
      Ok(views.html.homepage_admin(ProductForm.form,products))
    }
  }

/**
 * Add User.
 *
 * @return The result to display.
 */
  def addProduct() = Action.async { implicit request =>
  ProductForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.homepage_admin(errorForm,Seq.empty[Product]))),
      data => {
        val newProduct = Product(0,data.name,data.category,data.description,data.price,data.unitInStock)
        productService.addProduct(newProduct).map(res =>
          Redirect(routes.ProductController.home()).flashing(Messages("flash.success") -> res)
        )
      })
  }
  
/**
 * Delete User.
 *
 * @return The result to display.
 */
  def deleteProduct(id : Int) = Action.async { implicit request =>
  productService.deleteProduct(id) map { res =>
      Redirect(routes.ProductController.home())
    }
  }

}
