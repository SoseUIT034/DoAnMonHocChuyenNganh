package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by Canh on 5/14/2016.
  */
case class Address (id: Int, name: String, number: String, city: String, state: String, country: String, zipcode: String)

case class AddressFormData(name: String, number: String, city: String, state: String, country: String, zipcode: String)

object AddressForm {
  val form = Form (
    mapping (
      "name" -> nonEmptyText,
      "number" -> nonEmptyText,
      "city" -> nonEmptyText,
      "state" -> nonEmptyText,
      "country" -> nonEmptyText,
      "zipcode" -> nonEmptyText
    )(AddressFormData.apply)(AddressFormData.unapply)
  )
}
