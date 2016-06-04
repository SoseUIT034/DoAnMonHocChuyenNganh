package service

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Lucy on 5/20/2016.
  */

@ImplementedBy(classOf[CustomerServiceImpl])
trait CustomerService {
  def addCustomer(customer: Customer) : Future[String]
  def updateCustomer(id: Int, name: String, email: String, phone: Long, username: String, password: String, address: String) : Future[Int]
  def getCustomer(id : Int) : Future[Customer]
  def deleteCustomer(id : Int) : Future[Int]
  def listAllCustomers : Future[Seq[Customer]]
}
