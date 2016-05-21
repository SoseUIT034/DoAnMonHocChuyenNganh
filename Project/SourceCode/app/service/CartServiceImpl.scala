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
class CartServiceImpl @Inject()(cartDao: CartDAO) extends CartService {
  override def addCart(cart: Cart): Future[String] = {
    cartDao.add(cart)
  }

  override def deleteCart(id: Int): Future[Int] = {
    cartDao.delete(id)
  }

  override def getCart(id: Int): Future[Option[Cart]] = {
    cartDao.get(id)
  }

  override def listAllCarts: Future[Seq[Cart]] = {
    cartDao.listAll
  }
}