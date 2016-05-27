package controllers

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, Controller}
import service.ProductService
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future
import models.{Product,ProductForm}

/**
 * The Product controller.
 *
 * @param messagesApi The Play messages API.
 * @param productService The user service implementation.
 */
 class ProductController @Inject() (productService: ProductService, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  /** Home Page.*/
  def listProduct = Action.async { implicit  request =>
  productService.listAllProducts map { products =>
      Ok(views.html.list_product(ProductForm.form,products))
    }
  }

  /** List Product.*/
  def allProduct = Action.async { implicit  request =>
    productService.listAllProducts map { products =>
      Ok(views.html.product_admin(products))
    }
  }

  /* Add Product.*/
  def addProduct = Action { implicit request =>
    Ok(views.html.editproduct_admin(ProductForm.form))
  }
  /** Edit Product. */
  def editProduct(id: Int) = Action.async { implicit request =>
    productService.getProduct(id) map { product =>
      Ok(views.html.editproduct_admin(ProductForm.form))
    }
  }

  /** Update Product. */
  def updatedProduct(product: Product) = Action.async { implicit request =>
    ProductForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.editproduct_admin(errorForm))),
      data => {
        val newProduct = Product(0,data.name,data.category,data.description,data.price,data.unitInStock)
        productService.addProduct(newProduct).map(res =>
          Redirect(routes.ProductController.allProduct()).flashing(Messages("flash.success") -> res)
        )
      })
  }

   /** Save Product. */
  def savedProduct() = Action.async { implicit request =>
  ProductForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.editproduct_admin(errorForm))),
      data => {
        val newProduct = Product(0,data.name,data.category,data.description,data.price,data.unitInStock)
        productService.addProduct(newProduct).map(res =>
          Redirect(routes.ProductController.allProduct()).flashing(Messages("flash.success") -> res)
        )
      })
  }



  /** Delete Product.*/
  def deleteProduct(id : Int) = Action.async { implicit request =>
  productService.deleteProduct(id) map { res =>
      Redirect(routes.ProductController.allProduct())
    }
  }

}
