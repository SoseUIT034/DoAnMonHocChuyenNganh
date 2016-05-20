package dao

import com.google.inject.ImplementedBy
import models.CartItem
import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@ImplementedBy(classOf[CartItemDAOImpl])
trait CartItemDAO {
  def add(cartItem: CartItem) : Future[String]
  def get(id : Long) : Future[Option[CartItem]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[CartItem]]
}
