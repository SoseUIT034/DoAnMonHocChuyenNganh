package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models.Product
import play.api.Play._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@Singleton
class ProductDAOImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends ProductDAO{

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  override def add(product: Product): Future[String] = {
    db.run(ProductMap.productTableQuery += product).map(res => "Product Successfully Added").recover{
      case ex: Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Int): Future[Option[Product]] = {
    db.run(ProductMap.productTableQuery.filter(_.id === id).result.headOption)
  }

  override def delete(id: Int): Future[Int] = {
    db.run(ProductMap.productTableQuery.filter(_.id === id).delete)
  }

  override def listAll: Future[Seq[Product]] = {
    db.run(ProductMap.productTableQuery.result)
  }
}

object ProductMap {
  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](current)

  import dbConfig.driver.api._

  class ProductTable(tag: Tag) extends Table[Product](tag, "product"){

    def id = column[Int]("product_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("product_name")
    def category = column[String]("category")
    def description = column[String]("description")
    def price = column[Long]("price")
    def unitInStock = column[Int]("unitInStock")

    override def * = (id, name, category, description, price, unitInStock) <> (Product.tupled, Product.unapply)

  }

  implicit  val productTableQuery = TableQuery[ProductTable]
}


