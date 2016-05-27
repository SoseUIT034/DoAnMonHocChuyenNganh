package dao

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@ImplementedBy(classOf[CustomerDAOImpl])
trait CustomerDAO {
  def add(customer: Customer) : Future[String]
  def get(id : Int) : Future[Option[Customer]]
  def delete(id : Int) : Future[Int]
  def listAll : Future[Seq[Customer]]
}
