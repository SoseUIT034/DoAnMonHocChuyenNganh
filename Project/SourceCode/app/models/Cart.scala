package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Canh on 5/14/2016.
  */
case class Cart (id: Int, customerId: Int, grandTotal: Long)

case class CartFormData (customer: CustomerFormData, grandTotal: Long)

object CartForm {
  val form: Form[CartFormData] = Form(
    mapping(
      "customer" -> mapping(
        "name" -> nonEmptyText,
        "email" -> email,
        "phone" -> longNumber,
        "username" -> nonEmptyText,
        "password" -> nonEmptyText,
        "address" -> nonEmptyText
      )(CustomerFormData.apply)(CustomerFormData.unapply),
      "grandTotal" -> longNumber
    )(CartFormData.apply)(CartFormData.unapply)
  )
}