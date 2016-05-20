package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.Customer
import play.api.Play.current
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@Singleton
class CustomerDAOImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends CustomerDAO {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def add(customer: Customer): Future[String] = ???

  override def get(id: Long): Future[Option[Customer]] = ???

  override def delete(id: Long): Future[Int] = ???

  override def listAll: Future[Seq[Customer]] = ???
}

object CustomerMap {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)

  import dbConfig.driver.api._

  class CustomerTable(tag: Tag) extends Table[Customer](tag, "customer") {

    def id = column[Int]("customer_id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("customer_name")

    def email = column[String]("customer_email")

    def phone = column[Double]("customer_phone")

    def username = column[String]("username")

    def password = column[String]("password")

    def address = column[Int]("address")


    override def * = (id, name, email, phone, username, password, address) <>(Customer.tupled, Customer.unapply)
  }

  implicit val customerTableQuery = TableQuery[CustomerTable]
}
