package dao
import models.Customer
import play.api.Play.current
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

object CustomerDAOImpl extends CustomerDAO {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)
  import dbConfig.driver.api._

  class CustomerTable( tag: Tag) extends Table[Customer](tag,"customer") {

    def id = column[Int]("customer_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("customer_name")
    def email = column[String]("customer_email")
    def phone = column[Double]("customer_phone")
    def username = column[String]("username")
    def password = column[String]("password")
    def addressId = column[Int]("address_id")

    def address = foreignKey("address_customer_pk", addressId, AddressDAOImpl.addressTableQuery)


    override def * = (id, name, email, phone, username, password, addressId)<>(Customer.tupled, Customer.unapply)

  }

  implicit val customerTableQuery = TableQuery[CustomerTable]

  override def add(customer: Customer): Future[String] = ???

  override def get(id: Long): Future[Option[Customer]] = ???

  override def delete(id: Long): Future[Int] = ???

  override def listAll: Future[Seq[Customer]] = ???
}
