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
  def addCustomer() = Action.async { implicit request =>
    customerService.deleteCustomer(1) map { res =>
      Redirect(routes.CustomerController.listCustomer())
    }
  }

  /** Save Customer. */
  def savedCustomer() = Action.async { implicit request =>
    CustomerForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.editcustomer_admin(errorForm,Seq.empty[Customer]))),
      data => {
        val newCustomer = Customer(0,data.name,data.email,data.phone,data.address,data.username, data.password)
        customerService.addCustomer(newCustomer).map(res =>
          Redirect(routes.CustomerController.listCustomer()).flashing(Messages("flash.success") -> res)
        )
      })
  }
  /** Edit Customer. */
  def editCustomer() = Action.async { implicit request =>
    CustomerForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.editcustomer_admin(errorForm,Seq.empty[Customer]))),
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
