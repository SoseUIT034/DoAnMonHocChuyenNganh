package service

import com.google.inject.ImplementedBy
import models._

import scala.concurrent.Future

/**
  * Created by Lucy on 5/20/2016.
  */

@ImplementedBy(classOf[ProductServiceImpl])
trait ProductService {
  def addProduct(product: Product) : Future[String]
  def getProduct(id : Int) : Future[Option[Product]]
  def deleteProduct(id : Int) : Future[Int]
  def listAllProducts : Future[Seq[Product]]
}
