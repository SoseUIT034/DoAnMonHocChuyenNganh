package service

/**
  * Created by Lucy on 5/20/2016.
  */
import javax.inject.Inject

import com.google.inject.Singleton
import dao._
import models._

import scala.concurrent.Future

@Singleton
class CustomerServiceImpl @Inject()(customerDao: CustomerDAO) extends CustomerService {
  override def addCustomer(customer: Customer): Future[String] = {
    customerDao.add(customer)
  }

  override def deleteCustomer(id: Int): Future[Int] = {
    customerDao.delete(id)
  }

  override def getCustomer(id: Int): Future[Option[Customer]] = {
    customerDao.get(id)
  }

  override def listAllCustomers: Future[Seq[Customer]] = {
    customerDao.listAll
  }
}