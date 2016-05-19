package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.Customer
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by Canh on 5/14/2016.
  */

@Singleton
class CustomerDAOImpl @Inject() (dbConfigProvider: DatabaseConfigProvider) extends CustomerDAO{

  val dbConfig = dbConfigProvider.get[JdbcProfile]



  override def add(customer: Customer): Future[String] = ???

  override def get(id: Long): Future[Option[Customer]] = ???

  override def delete(id: Long): Future[Int] = ???

  override def listAll: Future[Seq[Customer]] = ???
}



class CustomerTable( tag: Tag) extends Table[Customer](tag,"customer") {

  import dbConfig._

  import driver.api._

  def id = column[Int]("customer_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("customer_name")
  def email = column[String]("customer_email")
  def phone = column[Double]("customer_phone")
  def username = column[String]("username")
  def password = column[String]("password")
  def addressId = column[Int]("address_id")

  def address = foreignKey("address_customer_pk", addressId, addressTableQuery)

  override def * : ProvenShape[Customer] = ???
}