package dao

import com.google.inject.ImplementedBy
import models.Cart_Item
import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@ImplementedBy(classOf[CartItemDAOImpl])
trait CartItemDAO {
  def add(cartItem: Cart_Item) : Future[String]
  def get(id : Int) : Future[Option[Cart_Item]]
  def delete(id : Int) : Future[Int]
  def listAll : Future[Seq[Cart_Item]]
}
