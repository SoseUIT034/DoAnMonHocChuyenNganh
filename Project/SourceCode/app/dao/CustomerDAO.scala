package dao

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Canh on 5/14/2016.
  */

@ImplementedBy(classOf[CustomerDAOImpl])
trait CustomerDAO {
  def add(customer: Customer) : Future[String]
  def get(id : Long) : Future[Option[Customer]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[Customer]]

}
