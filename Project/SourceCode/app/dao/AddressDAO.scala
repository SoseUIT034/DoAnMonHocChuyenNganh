package dao

import models._
import com.google.inject.ImplementedBy
import scala.concurrent.Future

/**
  * Created by Canh on 5/14/2016.
  */

@ImplementedBy(classOf[AddressDAOImpl])
trait AddressDAO {
  def add(address: Address) : Future[String]
  def get(id : Long) : Future[Option[Address]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[Address]]
}
