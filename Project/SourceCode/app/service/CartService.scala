package service

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Lucy on 5/20/2016.
  */

@ImplementedBy(classOf[CartServiceImpl])
trait CartService {
  def addCart(cart: Cart) : Future[String]
  def getCart(id : Int) : Future[Option[Cart]]
  def deleteCart(id : Int) : Future[Int]
  def listAllCarts : Future[Seq[Cart]]
}
