package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.Customer
import play.api.Play.current
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@Singleton
class CustomerDAOImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends CustomerDAO {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  override def add(customer: Customer): Future[String] = {
    db.run(CustomerMap.customerTableQuery += customer).map(res => "Customer Successfully Added").recover{
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Int): Future[Option[Customer]] = {
    db.run(CustomerMap.customerTableQuery.filter(_.id === id).result.headOption)
  }

  override def delete(id: Int): Future[Int] = {
    db.run(CustomerMap.customerTableQuery.filter(_.id === id).delete)
  }

  override def listAll: Future[Seq[Customer]] = {
    db.run(CustomerMap.customerTableQuery.result)
  }
}

object CustomerMap {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)

  import dbConfig.driver.api._

  class CustomerTable(tag: Tag) extends Table[Customer](tag, "customer") {

    def id = column[Int]("customer_id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("customer_name")

    def email = column[String]("customer_email")

    def phone = column[Long]("customer_phone")

    def username = column[String]("username")

    def password = column[String]("password")

    def address = column[String]("address")


    override def * = (id, name, email, phone, username, password, address) <>(Customer.tupled, Customer.unapply)
  }

  implicit val customerTableQuery = TableQuery[CustomerTable]
}
