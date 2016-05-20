package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.Cart
import play.api.Play._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@Singleton
class CartDAOImpl @Inject() (dbConfigProvider: DatabaseConfigProvider) extends CartDAO {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  override def add(cart: Cart): Future[String] = {
    db.run(CartMap.cartTableQuery += cart).map(res => "Cart Successfully Added").recover{
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Long): Future[Option[Cart]] = {
    db.run(CartMap.cartTableQuery.filter(_.cartId === id).result.headOption)
  }

  override def delete(id: Long): Future[Int] = {
    db.run(CartMap.cartTableQuery.filter(_.cartId === id).delete)
  }

  override def listAll: Future[Seq[Cart]] = {
    db.run(CartMap.cartTableQuery.result)
  }
}

object CartMap {
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)

  import dbConfig.driver.api._
  class CartTable(tag: Tag) extends Table[Cart](tag, "cart"){

    def cartId = column[Int]("cart_id",O.PrimaryKey, O.AutoInc)
    def customerId = column[Int]("customer_id")
    def grandTotal = column[Long]("grand_total")

    def customer = foreignKey("cart_customer_pk", customerId, CustomerMap.customerTableQuery)(_.id)

    override def * = (cartId.?, customerId, grandTotal)<>(Cart.tupled, Cart.unapply)
  }

  implicit val cartTableQuery = TableQuery[CartTable]

}
