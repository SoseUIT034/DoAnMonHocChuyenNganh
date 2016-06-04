package dao

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@ImplementedBy(classOf[ProductDAOImpl])
trait ProductDAO {
  def add(product: Product) : Future[String]
  def get(id : Int) : Future[Product]
  def delete(id : Int) : Future[Int]
  def update(id: Int, name: String, category: String, description: String, price: Long, unitInStock: Int) : Future[Int]
  def listAll : Future[Seq[Product]]
}
