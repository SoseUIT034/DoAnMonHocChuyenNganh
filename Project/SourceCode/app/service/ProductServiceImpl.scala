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
class ProductServiceImpl @Inject()(productDao: ProductDAO) extends ProductService {
  override def addProduct(product: Product): Future[String] = {
    productDao.add(product)
  }

  override def deleteProduct(id: Int): Future[Int] = {
    productDao.delete(id)
  }

  override def getProduct(id: Int): Future[Option[Product]] = {
    productDao.get(id)
  }

  override def listAllProducts: Future[Seq[Product]] = {
    productDao.listAll
  }
}