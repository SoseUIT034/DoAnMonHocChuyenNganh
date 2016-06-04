package controllers

import com.google.inject.Inject
import models.{Customer, CustomerForm}
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc.{Action, Controller}
import service.CustomerService

import scala.concurrent.Future

/**
 * The customer controller.
 *
 * @param messagesApi The Play messages API.
 * @param customerService The user service implementation.
 */
 class CustomerController @Inject()(customerService: CustomerService, val messagesApi: MessagesApi) extends Controller with I18nSupport {


  /** List Customer.*/
  def listCustomer = Action.async { implicit  request =>
    customerService.listAllCustomers map { customers =>
      Ok(views.html.customer_admin(CustomerForm.form,customers))
    }
  }

  /** Add Customer. */
/*  def addCustomer = Action {implicit  request =>

  }*/
  def addCustomer() = Action { implicit request =>
    Ok(views.html.addcustomer_admin(CustomerForm.form))
  }

  /** Edit Customer. */
  def editCustomer(id: Int) = Action.async { implicit request =>
    customerService.getCustomer(id) map { customer =>
      Ok(views.html.editcustomer_admin(CustomerForm.form, customer))
    }
  }

  /** Update Product. */
  def updatedCustomer(id: Int) = Action.async { implicit request =>
    CustomerForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.editcustomer_admin(errorForm,Customer(0,"","",0,"","","")))),
      data => {
        customerService.updateCustomer(id,data.name,data.email,data.phone,data.address,data.username,data.password).map(res =>
          Redirect(routes.CustomerController.listCustomer())
        )
      })
  }
  /** Save Customer. */
  def savedCustomer() = Action.async { implicit request =>
    CustomerForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.addcustomer_admin(errorForm))),
      data => {
        val newCustomer = Customer(0,data.name,data.email,data.phone,data.address,data.username, data.password)
        customerService.addCustomer(newCustomer).map(res =>
          Redirect(routes.CustomerController.listCustomer()).flashing(Messages("flash.success") -> res)
        )
      })
  }

  /** Delete Customer.*/
  def deleteCustomer(id : Int) = Action.async { implicit request =>
    customerService.deleteCustomer(id) map { res =>
      Redirect(routes.CustomerController.listCustomer())
    }
  }
}
