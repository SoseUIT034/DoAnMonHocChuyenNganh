package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Canh on 5/14/2016.
  */
case class Customer (id: Int, name: String, email: String, phone: Long, address: String, username: String, password: String)

case class CustomerFormData(name: String, email: String, phone: Long, address: String, username: String, password: String)

//case class CustomerFormData1(name: String, email: String, phone: Long, username: String, password: String, address: AddressFormData)

object CustomerForm {
  val form = Form(
    mapping (
      "name" -> nonEmptyText,
      "email" -> email,
      "phone" -> longNumber,
      "address" -> nonEmptyText,
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(CustomerFormData.apply)(CustomerFormData.unapply)
  )

//  val fullForm = Form (
//    tuple(
//      "Customer" -> mapping(
//        "name" -> nonEmptyText,
//        "email" -> email,
//        "phone" -> longNumber,
//        "username" -> nonEmptyText,
//        "password" -> nonEmptyText,
//        "addressId" -> number
//      )(CustomerFormData.apply)(CustomerFormData.unapply),
//
//      "address" -> mapping(
//        "name" -> nonEmptyText,
//        "number" -> nonEmptyText,
//        "city" -> nonEmptyText,
//        "state" -> nonEmptyText,
//        "country" -> nonEmptyText,
//        "zipcode" -> nonEmptyText
//      )(AddressFormData.apply)(AddressFormData.unapply)
//    )
//  )
//
//  val tempForm = Form (
//    mapping(
//      "name" -> nonEmptyText,
//      "email" -> email,
//      "phone" -> longNumber,
//      "username" -> nonEmptyText,
//      "password" -> nonEmptyText,
//      "address" -> mapping(
//        "name" -> nonEmptyText,
//        "number" -> nonEmptyText,
//        "city" -> nonEmptyText,
//        "state" -> nonEmptyText,
//        "country" -> nonEmptyText,
//        "zipcode" -> nonEmptyText
//      )(AddressFormData.apply)(AddressFormData.unapply)
//    )(CustomerFormData1.apply)(CustomerFormData1.unapply)
//  )
}
