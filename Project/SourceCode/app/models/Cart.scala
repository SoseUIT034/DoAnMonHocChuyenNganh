package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Canh on 5/14/2016.
  */
case class Cart (id: Int, customerId: Int, grandTotal: Long)

case class CartFormData (customer: CustomerFormData1, grandTotal: Long)

object CartForm {
  val form: Form[CartFormData] = Form(
    mapping(
      "customer" -> mapping(
        "name" -> nonEmptyText,
        "email" -> email,
        "phone" -> longNumber,
        "username" -> nonEmptyText,
        "password" -> nonEmptyText,
        "address" -> mapping(
          "name" -> nonEmptyText,
          "number" -> nonEmptyText,
          "city" -> nonEmptyText,
          "state" -> nonEmptyText,
          "country" -> nonEmptyText,
          "zipcode" -> nonEmptyText
        )(AddressFormData.apply)(AddressFormData.unapply)
      )(CustomerFormData1.apply)(CustomerFormData1.unapply),
      "grandTotal" -> longNumber
    )(CartFormData.apply)(CartFormData.unapply)
  )
}