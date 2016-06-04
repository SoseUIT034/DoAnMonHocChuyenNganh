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
  override def addCartItem(cartItem: Cart_Item): Future[String] = {
    cartItemDao.add(cartItem)
  }

  override def deleteCartItem(id: Int): Future[Int] = {
    cartItemDao.delete(id)
  }

  override def getCartItem(id: Int): Future[Option[Cart_Item]] = {
    cartItemDao.get(id)
  }

  override def listAllCartItems: Future[Seq[Cart_Item]] = {
    cartItemDao.listAll
  }
}