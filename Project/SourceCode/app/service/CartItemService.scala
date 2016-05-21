package service

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Lucy on 5/20/2016.
  */

@ImplementedBy(classOf[CartItemServiceImpl])
trait CartItemService {
  def addCartItem(cartItem: CartItem) : Future[String]
  def getCartItem(id : Int) : Future[Option[CartItem]]
  def deleteCartItem(id : Int) : Future[Int]
  def listAllCartItems : Future[Seq[CartItem]]
}
