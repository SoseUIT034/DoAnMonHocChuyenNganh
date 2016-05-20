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
  def get(id : Long) : Future[Option[Cart]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[Cart]]
}
