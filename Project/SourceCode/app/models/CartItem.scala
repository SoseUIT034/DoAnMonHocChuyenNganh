package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Canh on 5/14/2016.
  */
case class CartItem (id: Int, cartId: Int, productId: Int)

case class CartItemFormData (cart: CartFormData, product: ProductFormData)

object CartItemForm{
  val form = Form(
    mapping (
      "cart" -> mapping(
        "customer" -> mapping(
          "name" -> nonEmptyText,
          "email" -> email,
          "phone" -> longNumber,
          "username" -> nonEmptyText,
          "password" -> nonEmptyText,
          "address" -> nonEmptyText
        )(CustomerFormData.apply)(CustomerFormData.unapply),
        "grandTotal" -> longNumber
      )(CartFormData.apply)(CartFormData.unapply),
      "product" -> mapping(
        "name" -> nonEmptyText,
        "category" -> nonEmptyText,
        "description" -> nonEmptyText,
        "price" -> longNumber,
        "unitInStock" -> number
      )(ProductFormData.apply)(ProductFormData.unapply)
    )(CartItemFormData.apply)(CartItemFormData.unapply)
  )
}


