package dao

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Canh on 5/20/2016.
  */

@ImplementedBy(classOf[CartDAOImpl])
trait CartDAO {
  def add(cart: Cart) : Future[String]
  def get(id : Int) : Future[Option[Cart]]
/*  def getCartId(custId : Int) : Cart*/
  def delete(id : Int) : Future[Int]
  def listAll : Future[Seq[Cart]]
}
