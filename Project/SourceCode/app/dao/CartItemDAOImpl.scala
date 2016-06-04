package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.Cart_Item
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

  override def add(cartItem: Cart_Item): Future[String] = {
    db.run(CartItemMap.cartItemTableQuery += cartItem).map(res => "CartItem Successfully CartItem").recover{
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Int): Future[Option[Cart_Item]] = {
    db.run(CartItemMap.cartItemTableQuery.filter(_.cartItemId === id).result.headOption)
  }

  override def delete(id: Int): Future[Int] = {
    db.run(CartItemMap.cartItemTableQuery.filter(_.cartItemId === id).delete)
  }

  override def listAll: Future[Seq[Cart_Item]] = {
    db.run(CartItemMap.cartItemTableQuery.result)
  }
}

object CartItemMap {
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)

  import dbConfig.driver.api._
  class CartItemTable(tag: Tag) extends Table[Cart_Item](tag, "cart_item"){

    def cartItemId = column[Int]("cart_item_id",O.PrimaryKey, O.AutoInc)
    def cartId = column[Int]("cart_id")
    def productId = column[Int]("product_id")

    def customer = foreignKey("cartItem_cart_pk", cartId, CartMap.cartTableQuery)(_.cartId)
    def product = foreignKey("cartItem_product_pk", productId, ProductMap.productTableQuery)(_.id)

    override def * = (cartItemId, cartId, productId)<>(Cart_Item.tupled, Cart_Item.unapply)
  }

  implicit val cartItemTableQuery = TableQuery[CartItemTable]
}
