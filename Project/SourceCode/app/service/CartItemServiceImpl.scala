package service

/**
  * Created by Lucy on 5/20/2016.
  */
import javax.inject.Inject

import com.google.inject.Singleton
import dao._
import models._

import scala.concurrent.Future

@Singleton
class CartItemServiceImpl @Inject()(cartItemDao: CartItemDAO) extends CartItemService {
  override def addCartItem(cartItem: CartItem): Future[String] = {
    cartItemDao.add(cartItem)
  }

  override def deleteCartItem(id: Int): Future[Int] = {
    cartItemDao.delete(id)
  }

  override def getCartItem(id: Int): Future[Option[CartItem]] = {
    cartItemDao.get(id)
  }

  override def listAllCartItems: Future[Seq[CartItem]] = {
    cartItemDao.listAll
  }
}