package service

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Lucy on 5/20/2016.
  */

@ImplementedBy(classOf[CartItemServiceImpl])
trait CartItemService {
  def addCartItem(cartItem: Cart_Item) : Future[String]
  def getCartItem(id : Int) : Future[Option[Cart_Item]]
  def deleteCartItem(id : Int) : Future[Int]
  def listAllCartItems : Future[Seq[Cart_Item]]
}
