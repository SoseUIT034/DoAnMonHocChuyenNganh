package controllers

import java.text.Normalizer.Form

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, Controller}
import service.ProductService
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future
import models.{Product, ProductForm, ProductFormData}

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
    Ok(views.html.addproduct_admin(ProductForm.form))
  }
  /** Edit Product. */
  def editProduct(id: Int) = Action.async { implicit request =>
      productService.getProduct(id) map { product =>
        Ok(views.html.editproduct_admin(ProductForm.form, product))
      }
  }

  /** Update Product. */
  def updatedProduct(id: Int) = Action.async { implicit request =>
    ProductForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.editproduct_admin(errorForm,Product(0,"","","",0,0)))),
      data => {
        productService.updateProduct(id,data.name,data.category,data.description,data.price,data.unitInStock).map(res =>
          Redirect(routes.ProductController.allProduct())
        )
      })
  }
   /** Save Product. */
  def savedProduct() = Action.async { implicit request =>
  ProductForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.addproduct_admin(errorForm))),
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
