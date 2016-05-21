package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.CartItem
import play.api.Play._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@Singleton
class CartItemDAOImpl @Inject() (dbConfigProvider: DatabaseConfigProvider) extends CartItemDAO {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  override def add(cartItem: CartItem): Future[String] = {
    db.run(CartItemMap.cartItemTableQuery += cartItem).map(res => "CartItem Successfully CartItem").recover{
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Int): Future[Option[CartItem]] = {
    db.run(CartItemMap.cartItemTableQuery.filter(_.cartItemId === id).result.headOption)
  }

  override def delete(id: Int): Future[Int] = {
    db.run(CartItemMap.cartItemTableQuery.filter(_.cartItemId === id).delete)
  }

  override def listAll: Future[Seq[CartItem]] = {
    db.run(CartItemMap.cartItemTableQuery.result)
  }
}

object CartItemMap {
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)

  import dbConfig.driver.api._
  class CartItemTable(tag: Tag) extends Table[CartItem](tag, "cartItem"){

    def cartItemId = column[Int]("cart_item_id",O.PrimaryKey, O.AutoInc)
    def cartId = column[Int]("cart_id")
    def productId = column[Int]("product_id")

    def customer = foreignKey("cartItem_cart_pk", cartId, CartMap.cartTableQuery)(_.cartId)
    def product = foreignKey("cartItem_product_pk", productId, ProductMap.productTableQuery)(_.id)

    override def * = (cartItemId, cartId, productId)<>(CartItem.tupled, CartItem.unapply)
  }

  implicit val cartItemTableQuery = TableQuery[CartItemTable]
}
