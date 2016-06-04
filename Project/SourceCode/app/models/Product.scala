package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Canh on 5/14/2016.
  */
case class Product (id: Int, name: String, category: String, description: String, price: Long, unitInStock: Int)

case class ProductFormData (name: String, category: String, description: String, price: Long, unitInStock: Int)

object ProductForm {
  val form = Form {
    mapping(
      "name" -> nonEmptyText,
      "category" -> nonEmptyText,
      "description" -> nonEmptyText,
      "price" -> longNumber,
      "unitInStock" -> number
    )(ProductFormData.apply)(ProductFormData.unapply)
  }
}
